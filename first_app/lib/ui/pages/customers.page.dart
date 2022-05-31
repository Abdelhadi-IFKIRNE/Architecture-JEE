import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import '../../model/customer.model.dart';

class CustomerList extends StatefulWidget {
  const CustomerList({Key? key}) : super(key: key);

  @override
  State<CustomerList> createState() => _CustomerListState();
}

class _CustomerListState extends State<CustomerList> {

  Future<List<Customer>> _getAllCustomers() async{

    var data= await http.get(Uri.http("localhost:8080", '/customers'));
    var jsondata=json.decode(data.body);
    List<Customer> customers=[];

    for(var c in jsondata){
      Customer customer=Customer(c["id"],c["name"],c["email"]);
      customers.add(customer);
    }
    print(customers.length);
    return customers;
  }
  @override
  Widget build(BuildContext context) {


    return Scaffold(
      appBar: AppBar(title: Text("List of customers"),),
      body: Container(
        child: FutureBuilder(
          future: _getAllCustomers(),
          builder: (BuildContext context,AsyncSnapshot snapshot ){
            if(snapshot.data==null){
              return Container(child: Center(child: Text ("attendez")),);
            }else{
              return ListView.builder(
                itemCount: snapshot.data.length,
                itemBuilder: (BuildContext context,int index){
                  return Card( child: ListTile(
                    title: Text(snapshot.data[index].name),
                    subtitle: Text(snapshot.data[index].email),
                  ),);
                },
              );
            }
          },
        ),
      ),
    );
  }
}
