use oms2;

insert into Roles(RoleName) values('Administrator');
insert into Roles(RoleName) values('Merchandiser');
insert into Roles(RoleName) values('Supervisor');
insert into Roles(RoleName) values('Customer');


insert into Regions(RegionName) values('North');
insert into Regions(RegionName) values('East');
insert into Regions(RegionName) values('South');
insert into Regions(RegionName) values('West');
insert into Regions(RegionName) values('Center');


insert into OrderStatuses(OrederStatusName) values('Created');
insert into OrderStatuses(OrederStatusName) values('Pending');
insert into OrderStatuses(OrederStatusName) values('Ordered');
insert into OrderStatuses(OrederStatusName) values('Delivered');

insert into Dimensions(DimensionName,NumberOfProducts) values('Item',1);
insert into Dimensions(DimensionName,NumberOfProducts) values('Box',5);
insert into Dimensions(DimensionName,NumberOfProducts) values('Package',10);

insert into CustomerTypes(Discount,MaxRange,MinRange,TypeName) values(0,1000,0,'Standart');
insert into CustomerTypes(Discount,MaxRange,MinRange,TypeName) values(0,3000,1000,'Silver');
insert into CustomerTypes(Discount,MaxRange,MinRange,TypeName) values(0,10000,3000,'Gold');
insert into CustomerTypes(Discount,MaxRange,MinRange,TypeName) values(0,null,10000,'Platinum');

insert into Users(ID, IsUserActive,Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(1, true,0,'nellytest','Nelly','Moore','admin1','qwerty',1,1,1);
insert into Users(ID, IsUserActive,Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(2, true,0,'bobtest','Bob','B.','merch1','qwerty',2,2,2);
insert into Users(ID, IsUserActive,Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(3, true,0,'anntest','Ann','Lee','supervisor1','qwerty',1,3,3);
insert into Users(ID, IsUserActive,Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(4, true,0,'marytest','Mary','S.','customer1','qwerty',1,1,4);
insert into Users(ID, IsUserActive,Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(5, true,0,'nellytest','Nelly','Moore','admin','pass',1,1,1);


