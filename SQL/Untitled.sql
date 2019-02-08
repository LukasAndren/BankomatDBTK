create table if not exists customer 
(id int not null auto_increment,
name varchar(50) not null,
pincode int not null,
primary key(id));

create table if not exists account
(id int not null auto_increment,
customerId int not null,
balance int,
aInterest float,
primary key(id),
foreign key(customerId) references customer(id) 
on update cascade on delete cascade);

create table if not exists loan
(id int not null auto_increment,
accountId int not null,
amount int,
balance int,
lInterest float,
paymentPlan int,
primary key (id),
foreign key (accountId) references account(id)
on update cascade on delete cascade);

create table if not exists history
(id int not null auto_increment,
date date,
sum int not null,
accountId int not null,
primary key (id),
foreign key (accountId) references account(id));
select * from history;

select * from history where date between date_sub(current_date, interval 1 month) and current_date() having accountId = 2 or 5;

select k.id, k.date, k.sum, k.accountId, account.customerId from (select * from history where date between date_sub(current_date, interval 1 month) and current_date() having accountId = 2 ) as k
left join account on k.accountId = account.id;

select * from account;
select * from customer;
select * from history;
select * from loan;

delimiter //
create procedure AddCustomer(in customerName varchar(50), in customerPin int)
begin

	declare exit handler for sqlexception
    begin
          rollback;
          select ('SQLEXCEPTION occurred, rollback done');
    end;
    
    start transaction;
    
    insert into customer (name, pincode) values
    (customerName, customerPin);
    
    commit;
end//
delimiter ;

delimiter //
create procedure UpdateCustomer(in customerId int, in customerName varchar(50))
begin

	declare exit handler for sqlexception
    begin
          rollback;
          select ('SQLEXCEPTION occurred, rollback done');
    end;
    
    start transaction;
    
		update customer set name = customerName where id = customerId;
        
	commit;
    
end//
delimiter ;

delimiter //
create procedure DeleteCustomer(in customerId int)
begin

	declare exit handler for sqlexception
    begin
          rollback;
          select ('SQLEXCEPTION occurred, rollback done');
    end;
    
    start transaction;
    
    delete from customer where id = customerId;
    
    commit;
    
end//
delimiter ;


drop procedure AddAccount;
delimiter //
create procedure AddAccount(in customerId int, in balance int, in interest float)
begin

	declare exit handler for sqlexception
    begin
          rollback;
          select ('SQLEXCEPTION occurred, rollback done');
    end;
    
    start transaction;
    
    insert into account (customerId, balance, aInterest) values
    (customerId, balance, interest);
    commit;
    
end//
delimiter ;
call AddAccount (1, 1, 1);
delimiter //
create procedure DeleteAccount(in accountId int)
begin

	declare exit handler for sqlexception
    begin
          rollback;
          select ('SQLEXCEPTION occurred, rollback done');
    end;
    
    start transaction;
    
    delete from account where id = accountId;
    
    commit;
    
end//
delimiter ;

delimiter //
create procedure AddFunds(in accountId int, in amount int)
begin
	
	declare oldBalance int;	
	
	declare exit handler for sqlexception
    begin
          rollback;
          select ('SQLEXCEPTION occurred, rollback done');
    end;
    
    select balance into oldBalance from account where id = accountId;
    
    start transaction;
    
   update account set balance = (oldBalance + amount) where id = accountId;
    insert into history (accountId, date, sum) values
    (accountId, current_date, amount);
    commit;
    
end//
delimiter ;

delimiter //
create procedure RemoveFunds(in accountId int, in amount int)
begin
	
	declare oldBalance int;	
	
	declare exit handler for sqlexception
    begin
          rollback;
          select ('SQLEXCEPTION occurred, rollback done');
    end;
    
    select balance into oldBalance from account where id = accountId;
    
    start transaction;
    
    update account set balance = (oldBalance - amount) where id = accountId;
	insert into history (accountId, date, sum) values
    (accountId, current_date, -amount);

    commit;
    
end//
delimiter ;

drop procedure RemoveFunds;
call RemoveFunds(2, 123);

delimiter //
create procedure ChangeAccountInterest(in accountId int, in newInterest float)
begin

	declare exit handler for sqlexception
    begin
          rollback;
          select ('SQLEXCEPTION occurred, rollback done');
    end;
    
    start transaction;
    
   update account set aInterest = newInterest where id = accountId;
    
    commit;
    
end//
delimiter ;

delimiter //
create procedure CreateLoan(in accountId int, in amount int, in interest float, in paymentPlan int)
begin

	declare exit handler for sqlexception
    begin
          rollback;
          select ('SQLEXCEPTION occurred, rollback done');
    end;
    
    start transaction;
    
insert into loan (accountId, amount, balance, lInterest, paymentPlan) values
(accountId, amount, amount, interest, paymentPlan);

    commit;
    
end//
delimiter ;

delimiter //
create procedure ChangeLoanInterest(in loanId int, in newInterest float)
begin

	declare exit handler for sqlexception
    begin
          rollback;
          select ('SQLEXCEPTION occurred, rollback done');
    end;
    
    start transaction;
    
   update loan set lInterest = newInterest where id = loanId;
    
    commit;
    
end//
delimiter ;

delimiter //
create procedure ChangePlan(in loanId int, in newPaymentPlan int)
begin

	declare exit handler for sqlexception
    begin
          rollback;
          select ('SQLEXCEPTION occurred, rollback done');
    end;
    
    start transaction;
    
   update loan set paymentPlan = newPaymentPlan where id = loanId;
    
    commit;
    
end//
delimiter ;

select * from account where customerId = ?;
select * from loan where customerId = ?;

select * from history where date between date_sub(current_date, interval 1 month) and current_date() having accountId = ?;

select * from history where date between ‘01/01/2019’(?) and ‘02/02/2019’(?) having accountId = ?;

delimiter //
create function LoanProfit(loanId int)
returns float
deterministic
begin

	declare profit float;
    declare amount int;
    declare interest float;
    
    select amount into amount from loan where
    id = loanId;
    
    select interest into interest from loan where 
    id = loanId;
    
    set interest = interest / 100;

	set profit = amount * interest;
    
    return profit;
    
end//
delimiter ;

delimiter //
create function MonthlyPayment(loanId int)
returns float
deterministic
begin

	declare payment float;
    declare amount int;
    declare interest float;
    declare plan int;
    
    select amount into amount from loan where
    id = loanId;
    
    select interest into interest from loan where 
    id = loanId;
    
    select paymentPlan into plan from loan where
    id = loanId;
    
    set interest = interest / 100;

	set amount = amount + (amount * interest);
    
    set payment = amount / plan;
    
    return payment;
    
end//
delimiter ;