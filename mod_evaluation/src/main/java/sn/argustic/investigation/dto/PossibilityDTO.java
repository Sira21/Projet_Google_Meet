package sn.argustic.investigation.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PossibilityDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6501452558887972415L;

	private Long id;
	private String label;
}
