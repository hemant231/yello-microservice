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

   aws ecs list-task-definitions

   #aws ecs create-service --cluster fargate-cluster --service-name fargate-service --task-definition sample-fargate:1 --desired-count 2 --launch-type "FARGATE" --network-configuration "awsvpcConfiguration={subnets=[subnet-abcd1234],securityGroups=[sg-abcd1234]}"
   
    if [[ $(aws ecs create-service --cluster test-cluster --service-name yello-ms-service --task-definition $revision | \
                   $JQ '.service.taskDefinition') != $revision ]]; then
        echo "Error updating service."
        return 1
    fi

    #list services

    aws ecs list-services --cluster test-cluster

    # wait for older revisions to disappear
    # not really necessary, but nice for demos
    for attempt in {1..30}; do
        if stale=$(aws ecs describe-services --cluster test-cluster --services yello-ms-service | \
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

	echo $HOME
	echo registering task

	#aws ecs register-task-definition --cli-input-json file://$HOME/tasks/fargate-task.json
	
	if revision=$(aws ecs register-task-definition --cli-input-json file://$HOME/yello-microservice/yello-ms-task.json | $JQ '.taskDefinition.taskDefinitionArn'); then
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
