import os
from flask import *
from werkzeug.utils import secure_filename
app=Flask(__name__)
from src.dbconnection import *
app.secret_key="123"
from src import myknn
@app.route('/login',methods=['post'])
def login():
    uname = request.form['uname']
    password = request.form['password']
    qry="select * from login where User_Name=%s and Password=%s"
    val=(uname,password)
    res = selectone(qry, val)
    if res is None:
        return jsonify({"task":"INVALIED USER NAME OR PASSWORD"})
    else:
        return jsonify({"task": "ok","id":res[0]})

@app.route('/farmer_reg_form',methods=['post'])
def farmer_reg_form():
    try:
        fname = request.form['fname']
        lname = request.form['lname']
        place = request.form['place']
        post = request.form['post']
        pin = request.form['pin']
        district = request.form['district']
        phone = request.form['phone']
        uname = request.form['uname']
        password = request.form['password']
        qry = "INSERT INTO `login` VALUES(NULL,%s,%s,'pending')"
        val1 = (uname, password)
        lid = iud(qry, val1)
        qry1 = "INSERT INTO `farmer` VALUES(NULL,%s,%s,%s,%s,%s,%s,%s,%s)"
        val = (str(lid),fname,lname, place, post, pin, district, phone)
        iud(qry1, val)
        return jsonify({"task": "SUCCESS"})
    except Exception as e:
        return jsonify({"task": "already exist"})

@app.route('/product_upload',methods=['post'])
def product_upload():
    fid=request.form[' fid']
    product_name=request.form['product_name']
    description=request.form['  description']
    image=request.files['image']
    quantity=request.form['quantity']
    price=request.form[' price']
    qry="INSERT INTO `product` VALUES(NULL, %s,%s,%s,%s,%s)"
    vall=(str(fid),product_name,description, image, quantity,price)
    iud(qry,vall)
    return jsonify({"task": "SUCCESS"})

# @app.route('/view_product',methods=['post'])
# def view_product():
#     fid=request.form[' fid']
#     qry="SELECT * FROM `product` WHERE `Farmer_ID`!=%s"
#     res=androidselectall(qry,str(fid))
#     return jsonify(res)
#


# @app.route('/addtocartproduct',methods=['post'])
# def addtocartproduct():
#     farmerid=request.form['shopid']
#     productid = request.form['pid']
#     quantity = request.form['qty']
#
#     id = request.form['lid']
#     print(id)
#     try:
#         qry = "SELECT MAX(`bill`.`Bill_ID`)+1 FROM `bill`"
#         s = selectonenew(qry)
#         if s[0] is None:
#             oid = 1
#         else:
#             oid = s[0]
#     except Exception as e:
#         oid = 1
#     qry = "INSERT INTO `order_product` VALUES(NULL,%s,%s,CURDATE(),%s,%s)"
#     val = (str(productid),str(farmerid),quantity,str(oid))
#     iud(qry, val)
#
#     return jsonify({'task': "success", 'orderid': oid})
#
#
#
# @app.route('/product_bill',methods=['post'])
# def product_bill():
#
#     billid=request.form['billid']
#     farmerid=request.form['fid']
#     qry="SELECT `order_product`.*,`product`.* FROM `product` JOIN `order_product` ON `order_product`.`Product_ID`=`product`.`Product_ID` WHERE `Bill_ID`=%s"
#     res=selectall2(qry,str(billid))
#     total=0
#     for i in res:
#         total=total+(int(i[4])*int(i[12]))
#
#
#     qry="INSERT INTO `bill` VALUES(NULL,%s,CURDATE(),%s,'pending')"
#     value=(str(farmerid),str(total))
#     iud(qry,value)
#     return jsonify({'task': "success"})
#



@app.route('/view_equpment',methods=['post'])
def view_equpment():
    # fid=request.form[' fid']
    qry="SELECT * FROM `agricultural_equipment`"
    res=androidselectallnew(qry)
    return jsonify(res)


@app.route('/equpment_order',methods=['post'])
def equpment_order():
    fid=request.form['fid']
    qry="SELECT * FROM `bill_equipment` WHERE `Farmer_ID`=%s and status='payed'"
    val=(fid)
    res=androidselectall(qry,val)
    return jsonify(res)



@app.route('/addtocartequpment',methods=['post'])
def addtocartequpment():
    farmerid=request.form['fid']
    equipmentid = request.form['eid']
    qty = request.form['qty']

    # bid = request.form['bid']
    # print(id)
    try:
        qry = "SELECT MAX(`Bill_Equipment_ID`)+1 FROM `bill_equipment`"
        s = selectonenew(qry)
        if s[0] is None:
            oid = 1
        else:
            oid=s[0]

    except Exception as e:
        oid = 1
    # qry = "INSERT INTO `bill_equipment` VALUES(NULL,%s,CURDATE(),%s,'pending')"
    # val = (farmerid,oid)
    # oid = iud(qry, val)
    qry = "INSERT INTO `order_equipment` VALUES(NULL,%s,%s,CURDATE(),%s,%s)"
    val = (str( equipmentid),str(farmerid),str(oid),qty)
    iud(qry, val)

    return jsonify({'task': "success", 'orderid': oid})






@app.route('/equpment_bill',methods=['post'])
def equpment_bill():

    bil_equ_lid=request.form['bil_equ_lid']
    farmerid=request.form['fid']
    qry="SELECT `agricultural_equipment`.*,`order_equipment`.* FROM `order_equipment` JOIN `agricultural_equipment` ON `agricultural_equipment`.`Equipment_ID`=`order_equipment`.`Equipment_ID` WHERE `order_equipment`.`Bill_Equipment_ID`=%s"
    res=selectall2(qry,str(bil_equ_lid))
    total=0
    for i in res:
        total=total+(int(i[4])*int(i[10]))


    qry="INSERT INTO `bill_equipment` VALUES(NULL,%s,CURDATE(),%s,'pending')"
    value=(str(farmerid),str(total))
    iud(qry,value)
    return jsonify({'task': "success","total":total})




@app.route('/view_equipmentcart',methods=['post'])
def view_equipmentcart():
    fid=request.form['fid']

    oid=request.form['oid']
    print(oid)
    b1=request.form['b1']
    if b1=="yes":


        qry="SELECT `order_equipment`.*,`agricultural_equipment`.`Name`,`agricultural_equipment`.`Price` FROM `agricultural_equipment` JOIN `order_equipment` ON `order_equipment`.`Equipment_ID`=`agricultural_equipment`.`Equipment_ID`   join  bill_equipment on `bill_equipment`.`Bill_Equipment_ID`= `order_equipment`.`Bill_Equipment_ID`  and `bill_equipment`.status='pending'  AND `order_equipment`.`Bill_Equipment_ID`=%s  and `order_equipment`.`Farmer_ID`=%s"

    else:
        qry = "SELECT `order_equipment`.*,`agricultural_equipment`.`Name`,`agricultural_equipment`.`Price` FROM `agricultural_equipment` JOIN `order_equipment` ON `order_equipment`.`Equipment_ID`=`agricultural_equipment`.`Equipment_ID`   join  bill_equipment on `bill_equipment`.`Bill_Equipment_ID`= `order_equipment`.`Bill_Equipment_ID`  and `bill_equipment`.status='payed'  AND `order_equipment`.`Bill_Equipment_ID`=%s  and `order_equipment`.`Farmer_ID`=%s"

    val = (str(oid), str(fid))
    res = androidselectall(qry, val)
    print(res)
    return jsonify(res)


@app.route('/payment_details_farmer',methods=['post'])
def payment_details_farmer():
    fid=request.form['fid']
    qry="SELECT `bill`.*,`shop`.`Shop_Name`,`PLace` FROM `bill` JOIN `shop` ON `bill`.`Shop_ID`=`shop`.`Login_ID` WHERE `bill`.`Bill_ID` IN(SELECT `Bill_ID` FROM `order_product` WHERE `Product_ID` IN(SELECT `Product_ID` FROM `product` WHERE `Farmer_ID`=%s))"
    val=(fid)
    print(val)
    res=androidselectall(qry,val)
    return jsonify(res)

@app.route('/post_complaints',methods=['post'])
def post_complaints():
    u_id = request.form['u_id']
    fid = request.form['fid']
    complaints = request.form['complaints']
    qry = "INSERT INTO `complaint`  VALUES(NULL,%s,%s,%s,CURDATE(),'pending')"
    val = (fid, u_id, complaints)
    iud(qry, val)
    return jsonify({"task":"SUCCESS"})

@app.route('/view_replay',methods=['post'])
def view_replay():
    lid=request.form['lid']
    qry = "SELECT `complaint`.`Complaint`,`complaint`.`Reply`,`complaint`.`Date`,shop.Shop_Name FROM `complaint` join shop  on shop.`Login_ID`=`complaint`.`Shop_ID` where Product_ID=%s"
    res = androidselectall(qry,lid)
    return jsonify(res)


@app.route('/view_products',methods=['post'])
def view_products():
    qry="SELECT * FROM `product`"
    res=androidselectallnew(qry)
    return jsonify(res)
@app.route('/bill',methods=['post'])
def bill():
    qry="SELECT `bill`.*,`shop`.* FROM `bill` JOIN `shop` ON `bill`.`Shop_ID`=`shop`.`Login_ID` where bill.status='pending'"
    res=androidselectallnew(qry)
    return jsonify(res)

@app.route('/order_details',methods=['post'])
def order_details():
    billid=request.form['billid']
    qry="SELECT `product`.* FROM `product` JOIN `order_product` ON `order_product`.`Product_ID`=`product`.`Product_ID` WHERE `order_product`.`Bill_ID`=%s"
    res=androidselectall(qry,billid)
    return jsonify(res)

@app.route('/add_products',methods=['post'])
def add_products():
    name=request.form['name']
    image=request.files['file']
    im= secure_filename(image.filename)
    image.save(os.path.join("static/uploads", im))
    description=request.form['description']
    quantity=request.form['qty']
    price=request.form['price']
    lid=request.form['lid']
    qry="INSERT INTO `product` VALUES(NULL,%s,%s,%s,%s,%s,%s)"
    val=(lid,name,description,im,quantity,price)
    iud(qry,val)
    return jsonify({"task": "SUCCESS"})



@app.route('/bank',methods=['post'])
def bank():
    Account_No=request.form['accno']
    ifsc = request.form['ifsc']
    key = request.form['key']
    Farmer_ID = request.form['fid']
    total=request.form['total']
    bil_equ_lid = request.form['bil_equ_lid']
    qry="SELECT * FROM `bank` WHERE `Account_No`=%s AND `IFSC`=%s AND`Key`=%s and farmer_id=%s"
    val=(Account_No,ifsc,key,Farmer_ID)
    res=selectone(qry,val)
    if res is None:

        return jsonify({"task": "Invalid account"})
    else:
        orgamt=int(res[4])
        if orgamt>int(total):
            qry="UPDATE `bank` SET `Amount`=Amount-%s WHERE `Farmer_ID`=%s"
            val=(total,Farmer_ID)
            iud(qry,val)
            qry = "update `bill_equipment` SET `Status`='payed' WHERE `Bill_Equipment_ID`=%s"
            val = (bil_equ_lid)
            iud(qry, val)
            # qry="select * from `order_equipment` WHERE `Bill_Equipment_ID`=%s"
            # res=selectall2(qry,bil_equ_lid)
            # for i in res:
            #     qry="update "

            return jsonify({"task": "success"})
        else:
            return jsonify({"task": "insufficient amount"})



@app.route('/acceptorder',methods=['post'])
def acceptorder():
    bilid=request.form['billid']
    qry="update bill set status='accepted' where Bill_ID=%s"
    iud(qry,bilid)
    return jsonify({"task": "success"})

@app.route('/rejecttorder',methods=['post'])
def rejecttorder():
    bilid=request.form['billid']
    qry="update bill set status='rejected' where Bill_ID=%s"
    iud(qry,bilid)
    return jsonify({"task": "success"})


@app.route('/view_shops',methods=['post'])
def view_shops():
    qry="SELECT * FROM `shop`"
    res=androidselectallnew(qry)
    return jsonify(res)

@app.route('/crop_prediction',methods=['post','get'])
def crop_prediction():
    humidity=request.form['humi']
    temperature=request.form['temp']
    wet=request.form['wet']

    # humidity=request.args.get('h')
    # temperature=request.args.get('t')
    # wet=request.args.get('w')
    print(request.form)
    row=[float(humidity),float(temperature),float(wet)]


    print(row)

    res = myknn.prep([row])
    print("id", res)
    if res is not None:

        qry="select * from crop where id  in ("+res+")"
        print(qry)
        res=androidselectallnew(qry)
        return jsonify(res)


    else:

        return jsonify({'task': 'no'})



app.run(host="0.0.0.0",port="5000")