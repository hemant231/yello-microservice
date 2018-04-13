package com.yello.api.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * This is just a plain POJO, No DB relation
 * 
 * @author kaushal
 * @version 1.0
 * @since 26.03.2018
 */
public class YelloServiceCallReport {

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date timeStamp;

	private int calls;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public Date getTimeStamp() {

		return this.timeStamp == null ? null : (Date) this.timeStamp.clone();
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = (Date) timeStamp.clone();
	}

	public int getCalls() {
		return calls;
	}

	public void setCalls(int calls) {
		this.calls = calls;
	}

	@Override
	public String toString() {
		return "YelloServiceCallReport [timeStamp=" + timeStamp + ", calls="
				+ calls + "]";
	}
}
