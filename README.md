# Database
Final project for this course.
Readme.md is for Mr.Zhou.
# 注意
- 首先用无参构造函数初始化，然后向JScrolPane中添加表格，使用setViewportView(component)
- 
"select top 5 SC.S_id as ID, S.s_name as Name, " +
                "sum(SC.score * Course.credit)/sum(Course.credit) as gpa " +
                "from Student as S, SC, Course where S.S_id = SC.S_id and " +
                "Course.C_id = SC.C_id group by SC.S_id, S.S_name having min(SC.score) >= 60 order by gpa;";
