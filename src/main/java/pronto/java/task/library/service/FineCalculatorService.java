package pronto.java.task.library.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pronto.java.task.library.model.Loan;

@Service
public class FineCalculatorService {
	
	@Value("{$loan.fine.perday}")
	private static double fineValue;
	
	public static List<Loan> applyFineCalculation(List<Loan> allActiveLoansByUser) {
		allActiveLoansByUser.stream().forEach(
				loan -> loan.setFineAmount(fineValue*getDateDiff(loan.getBorrowDate(),loan.getReturnDate())));
		return allActiveLoansByUser;
	}
	
	private static long getDateDiff(Date borrowdate, Date returndate) {
		borrowdate=borrowdate!=null ? borrowdate: new Date();
		returndate=returndate!=null ? returndate: new Date();
		
		long diffInMillies = Math.abs(returndate.getTime() - borrowdate.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    
	    return diff;
	}

}
