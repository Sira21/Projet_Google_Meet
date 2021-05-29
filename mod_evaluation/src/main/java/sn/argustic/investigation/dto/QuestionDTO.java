package sn.argustic.investigation.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1076504869219371987L;
	
	private Long id;
	private String label;
	private int noteMax;
	private Iterable<PossibilityDTO> possibilities;
}
