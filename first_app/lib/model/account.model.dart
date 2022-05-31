import 'customer.model.dart';

class Account {
  late String accountType;
  late String balance;
  late String id;
  late DateTime createdAt;
  late String status;
  late String currency;
  late double overDraft;
  late Customer customer;
  Account(this.accountType,this.balance,this.customer,this.createdAt,this.currency,this.overDraft,this.id,this.status);
}