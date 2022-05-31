import 'package:first_app/model/account.model.dart';
import 'package:first_app/ui/pages/account.page.dart';
import 'package:first_app/ui/pages/customers.page.dart';
import 'package:first_app/ui/pages/operation.page.dart';
import 'package:first_app/ui/pages/home.page.dart';
import 'package:flutter/material.dart';

void main()=>runApp(MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      routes: {
        "/home":(context)=>MyHome(),
        "/operations":(context)=>MyGallery(),
        "/accounts":(context)=>AccountList(),
        "/customers":(context)=>CustomerList(),
      },
       // home:MyHome()
      initialRoute: "/home",
    );
  }
}

























