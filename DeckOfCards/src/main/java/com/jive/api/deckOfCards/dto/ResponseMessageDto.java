package com.jive.api.deckOfCards.dto;



import java.io.Serializable;

import org.springframework.http.HttpHeaders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * @author Ashish.Patel
 *
 */

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResponseMessageDto implements Serializable {

	private static final long serialVersionUID = -6654830719050808718L;

	private int responseCode;

	private boolean isError;

	private String responseMessage;

	private String errorDiscription;
	
	private HttpHeaders httpHeaders;
	
	private Object responseObject;

}
