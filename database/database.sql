/**
*Author:Wch
*Date:2016-12-28
*Desc:创建人员组织架构
*/
/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/12/28 10:38:18                          */
/*==============================================================*/


drop table if exists r_emp_role;

drop table if exists r_role_pri;

drop table if exists t_dept;

drop table if exists t_employee;

drop table if exists t_leader;

drop table if exists t_org;

drop table if exists t_privilege;

drop table if exists t_role;

/*==============================================================*/
/* Table: r_emp_role                                            */
/*==============================================================*/
create table r_emp_role
(
   emp_id               varchar(32) not null,
   role_id              varchar(32) not null,
   state                int,
   primary key (emp_id, role_id)
);

/*==============================================================*/
/* Table: r_role_pri                                            */
/*==============================================================*/
create table r_role_pri
(
   role_id              varchar(32) not null,
   pri_id               varchar(32) not null,
   primary key (role_id, pri_id)
);

/*==============================================================*/
/* Table: t_dept                                                */
/*==============================================================*/
create table t_dept
(
   dept_id              varchar(32) not null,
   org_id               varchar(32) not null,
   name                 varchar(20),
   primary key (dept_id)
);

/*==============================================================*/
/* Table: t_employee                                            */
/*==============================================================*/
create table t_employee
(
   emp_id               varchar(32) not null,
   dept_id              varchar(32) not null,
   name                 varchar(20) not null,
   primary key (emp_id)
);

/*==============================================================*/
/* Table: t_leader                                              */
/*==============================================================*/
create table t_leader
(
   emp_id               varchar(32) not null,
   dept_id              varchar(32),
   name                 varchar(20) not null,
   persition            varchar(50),
   primary key (emp_id)
);

/*==============================================================*/
/* Table: t_org                                                 */
/*==============================================================*/
create table t_org
(
   org_id               varchar(32) not null,
   name                 varchar(20) not null,
   primary key (org_id)
);

/*==============================================================*/
/* Table: t_privilege                                           */
/*==============================================================*/
create table t_privilege
(
   pri_id               varchar(32) not null,
   name                 varchar(20) not null,
   primary key (pri_id)
);

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   role_id              varchar(32) not null,
   name                 varchar(20) not null,
   primary key (role_id)
);

alter table r_emp_role add constraint FK_r_emp_role foreign key (emp_id)
      references t_employee (emp_id) on delete restrict on update restrict;

alter table r_emp_role add constraint FK_r_emp_role2 foreign key (role_id)
      references t_role (role_id) on delete restrict on update restrict;

alter table r_role_pri add constraint FK_belong foreign key (role_id)
      references t_role (role_id) on delete restrict on update restrict;

alter table r_role_pri add constraint FK_own foreign key (pri_id)
      references t_privilege (pri_id) on delete restrict on update restrict;

alter table t_dept add constraint FK_r_org_dept foreign key (org_id)
      references t_org (org_id) on delete restrict on update restrict;

alter table t_employee add constraint FK_r_dept_emp foreign key (dept_id)
      references t_dept (dept_id) on delete restrict on update restrict;

alter table t_leader add constraint FK_inheritence_1 foreign key (emp_id)
      references t_employee (emp_id) on delete restrict on update restrict;
      
      
/**
*Author:Wch
*Date:2016-12-31
*Desc:投诉信息建表语句
*/
/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/12/31 18:27:06                          */
/*==============================================================*/


drop table if exists complain;

drop table if exists reply;

/*==============================================================*/
/* Table: complain                                              */
/*==============================================================*/
create table complain
(
   compId               varchar(32) not null,
   compName             varchar(20),
   compPhone            varchar(20),
   compTime             datetime,
   isAnony              bool,
   toCompDept           varchar(100),
   toCompName           varchar(20),
   compTitle            varchar(50),
   compComtent          text,
   state                varchar(1),
   primary key (compId)
);

/*==============================================================*/
/* Table: reply                                                 */
/*==============================================================*/
create table reply
(
   replyId              varchar(32) not null,
   compId               varchar(32) not null,
   replyDept            varchar(50),
   replyName            varchar(20),
   replyContent         text,
   replyTime            datetime,
   primary key (replyId)
);

alter table reply add constraint FK_t_comp_reply foreign key (compId)
      references complain (compId) on delete restrict on update restrict;

/**
*Author:Wch
*Date:2017-01-01
*Desc:更新完善投诉信息建表语句
*/

/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/1/1 12:49:08                            */
/*==============================================================*/


drop table if exists complain;

drop table if exists reply;

/*==============================================================*/
/* Table: complain                                              */
/*==============================================================*/
create table complain
(
   compId               varchar(32) not null,
   compName             varchar(20),
   compPhone            varchar(20),
   compTime             datetime,
   isAnony              bool,
   toCompDept           varchar(100),
   toCompName           varchar(20),
   compTitle            varchar(50),
   compComtent          text,
   state                varchar(1),
   compDept             varchar(100),
   primary key (compId)
);

/*==============================================================*/
/* Table: reply                                                 */
/*==============================================================*/
create table reply
(
   replyId              varchar(32) not null,
   compId               varchar(32) not null,
   replyDept            varchar(50),
   replyName            varchar(20),
   replyContent         text,
   replyTime            datetime,
   primary key (replyId)
);

alter table reply add constraint FK_t_comp_reply foreign key (compId)
      references complain (compId) on delete restrict on update restrict;

/**
*Author:Wch
*Date:2017-01-14
*Desc:预约信息建表语句
/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/1/14 15:41:48                           */
/*==============================================================*/


drop table if exists t_reserve;

drop table if exists t_reserveItem;

drop table if exists t_reserveReply;

/*==============================================================*/
/* Table: t_reserve                                             */
/*==============================================================*/
create table t_reserve
(
   reserveId            varchar(32) not null,
   itemId               varchar(32) not null,
   reserveItem          varchar(100),
   reserveName          varchar(100),
   reserveTime          timestamp,
   reserveAddress       varchar(100),
   reserveDecl          varchar(500),
   reserveNo            varchar(15),
   primary key (reserveId)
);

/*==============================================================*/
/* Table: t_reserveItem                                         */
/*==============================================================*/
create table t_reserveItem
(
   itemId               varchar(32) not null,
   itemName             varchar(102),
   itemDept             varchar(102),
   itemEmp              varchar(102),
   itemState            varchar(1),
   itemNo               varchar(15),
   primary key (itemId)
);

/*==============================================================*/
/* Table: t_reserveReply                                        */
/*==============================================================*/
create table t_reserveReply
(
   replyId              varchar(32) not null,
   reserveId            varchar(32) not null,
   replyEmp             varchar(20),
   replyDept            varchar(100),
   replyContent         varchar(1024),
   primary key (replyId)
);

alter table t_reserve add constraint FK_r_item_reserve foreign key (itemId)
      references t_reserveItem (itemId) on delete restrict on update restrict;

alter table t_reserveReply add constraint FK_r_reserve_reply foreign key (reserveId)
      references t_reserve (reserveId) on delete restrict on update restrict;



