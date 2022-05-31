import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import '../../model/account.model.dart';


class AccountList extends StatefulWidget {
  const AccountList({Key? key}) : super(key: key);

  @override
  State<AccountList> createState() => _AccountListState();
}

class _AccountListState extends State<AccountList> {

  Future<List<Account>> _getAllAccount() async{

    var data= await http.get(Uri.http("localhost:8080", '/accounts'));
    var jsonapi=json.decode(data.body);
    List<Account> accountList=[];

    for(var c in jsonapi){
      Account account=Account(c["accountType"],c["balance"],c["customer"],c["createdAt"],c["currency"],c["overDraft"],c["id"],c["status"]);
      accountList.add(account);
    }
    print(accountList[1].accountType);
    return accountList;
  }
  @override
  Widget build(BuildContext context) {


    return Scaffold(
      appBar: AppBar(title: Text("List of accounts"),),
      body: Container(
        child: FutureBuilder(
          future: _getAllAccount(),
          builder: (BuildContext context,AsyncSnapshot snapshot ){
            if(snapshot.data==null){
              return Container(child: Center(child: Text ("attendez....")),);
            }else{
              return ListView.builder(
                itemCount: snapshot.data.length,
                itemBuilder: (BuildContext context,int index){
                  return Card( child: ListTile(
                    title: Text(snapshot.data[index].accountType),
                    subtitle: Text(snapshot.data[index].balance),
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

