package services;


import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import beans.*;
import data.*;
import services.*;

@ExtendWith(MockitoExtension.class)
public class PitchTests {
	@Mock
	static PitchDAO pitchDao;
	
	@Mock
	static UsrDAO usrDao;
	
	@InjectMocks
	static PitchServiceImpl pitchServ;
	
	static Set<Approval> approvalMock = new HashSet<>();
	static Integer approvalSequenceMock = 1;
	
	static Set<Pitch> pitchMock = new HashSet<>();
	static Integer pitchSequenceMock = 1;
	
	@Test
	public void testAddandRetrieveValidPitch() { // Testing service layer through the DAO
		Pitch p = new Pitch();
		Genre b = new Genre();
		p.setP_id(1);
		Status s = new Status();
		s.setStatus_id(1);
		p.setGenre(b);
		p.setStatus(s);
		
		pitchMock.add(p);
		Pitch p2 = new Pitch();
		p2.setP_id(pitchSequenceMock++);
		p2.setGenre(p.getGenre());
		p2.setStatus(p.getStatus());
	}
	@Test
	public void testAddandRetrieveValidApproval() { // Testing service layer through the DAO
		Approval a = new Approval();
		Pitch p = new Pitch();
		Genre g = new Genre();
		a.setApproval_Id(1);
		g.setGenres_id(1);
		p.setGenre(g);
		a.setPitch(p);
		
		approvalMock.add(a);
		Approval a2 = new Approval();
		a2.setApproval_Id(approvalSequenceMock++);
		a2.setGenre(a.getGenre());
	}
}
