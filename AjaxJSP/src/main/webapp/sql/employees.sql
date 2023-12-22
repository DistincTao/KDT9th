SELECT E.*, D.DEPARTMENT_NAME
   FROM EMPLOYEES E INNER JOIN DEPARTMENTS D
     ON E.DEPARTMENT_ID = D.DEPARTMENT_ID;
     
SELECT * FROM JOBS;

SELECT * FROM DEPARTMENTS;

SELECT distinct m.EMPLOYEE_ID as manager_id, m.FIRST_NAME, m.last_NAME, d.department_id
  FROM EMPLOYEES e, EMPLOYEES m, departments d
 WHERE e.MANAGER_ID = m.EMPLOYEE_ID
   and d.department_Id = e.department_id
 order by d.department_id;