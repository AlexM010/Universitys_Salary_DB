import java.sql.*;

public class Employee {
    private String name;
    /* Family Status*/
    private boolean married;
    private int numOfChildren;
    private int[] children;

    private Date startDate;

    /* Info */
    private String department;
    private String address;
    private String telephone_num;
    private String bank_name;
    private String IBAN;

    /* Staff type */
    private enum category{
        ADMINISTRATIVE,EDUCATIONAL
    };
    private int salaryId;


}
