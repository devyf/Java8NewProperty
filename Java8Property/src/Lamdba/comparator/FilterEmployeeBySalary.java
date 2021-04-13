package Lamdba.comparator;

public class FilterEmployeeBySalary implements MyPredicate<Employee>{
    //获取公司中工资大于5000的员工信息
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() >= 5000;
    }
}
