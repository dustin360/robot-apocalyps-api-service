package com.apocalypse.robot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneralResponse<T> {

	private int status;

	private String message;

	private T data;

	public GeneralResponse(int status, String msg) {
		this.status = status;
		this.message = msg;
	}

}
