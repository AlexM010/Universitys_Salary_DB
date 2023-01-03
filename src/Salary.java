import java.sql.*;

public class Salary {
    private int salaryId;
    /* Contract */
    private enum Contract{
        PERMANENT,CONTRACT
    };
    private Date startDate;
    private Date endDate;
    private int years;

    private boolean married;
    private int underage_children;

    /* Income */
    private double totalSalary;
    private double mainSalary;
    private double bonus;
}
