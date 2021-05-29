package sn.argustic.investigation.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7899355367957627338L;
	
	private int answer;
}
