package Lamdba.comparator;

public class FilterEmployeeByAge implements MyPredicate<Employee> {
    //需求：获取当前公司中员工年龄大于35的员工信息
    @Override
    public boolean test(Employee employee) {
       return employee.getAge() >= 35;
    }
}
