#!/usr/bin/env bash

# more bash-friendly output for jq
JQ="jq --raw-output --exit-status"

configure_aws_cli(){
	aws --version
	aws configure set default.region us-east-1
	aws configure set default.output json
}
deploy_cluster() {

   family="yello-family"

   make_and_register_task_def
   
    if [[ $(aws ecs update-service --cluster test-cluster --service test-service --task-definition $revision | \
                   $JQ '.service.taskDefinition') != $revision ]]; then
        echo "Error updating service."
        return 1
    fi

    # wait for older revisions to disappear
    # not really necessary, but nice for demos
    for attempt in {1..30}; do
        if stale=$(aws ecs describe-services --cluster test-cluster --services test-service | \
                       $JQ ".services[0].deployments | .[] | select(.taskDefinition != \"$revision\") | .taskDefinition"); then
            echo "Waiting for stale deployments:"
            echo "$stale"
            sleep 5
        else
            echo "Deployed!"
            return 0
        fi
    done
    echo "Service update took too long."
    return 1
}

make_and_register_task_def(){
	task_template='[
		{
			"name": "go-sample-webapp",
			"image": "$AWS_ACCOUNT_ID.dkr.ecr.us-east-2.amazonaws.com/yello-team:$CIRCLE_SHA1",
			"essential": true,
			"memory": 500,
			"cpu": 2,
			"portMappings": [
				{
					"containerPort": 8081,
					"hostPort": 8081
				}
			]
		}
	]'
	
	task_def=$(printf "$task_template" $AWS_ACCOUNT_ID $CIRCLE_SHA1)
	
	if revision=$(aws ecs register-task-definition --cli-input-json "$task_def" --family $family | $JQ '.taskDefinition.taskDefinitionArn'); then); then
        	echo "Revision: $revision"
	else
		echo "Failed to register task definition"
		return 1
	fi
}
push_ecr_image(){
	echo push_ecr_image
	eval $(aws ecr get-login --region us-east-2)
	docker push $AWS_ACCOUNT_ID.dkr.ecr.us-east-2.amazonaws.com/yello-team:$CIRCLE_SHA1
	docker push $AWS_ACCOUNT_ID.dkr.ecr.us-east-2.amazonaws.com/yello-team:latest

}

configure_aws_cli
push_ecr_image
deploy_cluster
