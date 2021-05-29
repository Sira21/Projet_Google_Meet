package sn.argustic.investigation.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Investigation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String reference;
	
	@Column(unique = true)
	private Long seance;
	
	@Column(name = "opening_date")
	private Date openingDate;
	@Column(name = "closing_date")
	private Date closingDate;
	
	private boolean closed;
}
