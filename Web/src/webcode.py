import os
from flask import *
from werkzeug.utils import secure_filename

app=Flask(__name__)
from src.dbconnection import *
app.secret_key="123"

import functools
def login_required(func):
    @functools.wraps(func)
    def secure_function():
        if "lid" not in session:
            return redirect ("/")
        return func()
    return secure_function

@app.route('/main')
def main():
    # return render_template('index.html')
    return render_template('login.html')

@app.route('/login',methods=['post'])
def login():
    uname =request.form['textfield']
    password =request.form['textfield2']
    qry="select * from login where User_Name=%s and Password=%s"
    val=(uname,password)
    print(val)
    res=selectone(qry,val)
    print(res)
    if res is None :
        return '''<script> alert("INVALIED USERNAME OR PASSWORD...!!!");window.location="/"</script>'''
    elif res[3]=='admin':
        session['lid']=res[0]
        return redirect("/ADMIN_HOME")
    elif res[3]=='krishibhavan':
        session['lid'] = res[0]
        return redirect("/KRISHIBHAVAN_HOME")
    elif res[3]=='shop':
        session['lid'] = res[0]
        return redirect("/SHOP_HOME")
    else :
        return '''<script> alert("INVALIED USERNAME OR PASSWORD...!!!");window.location="/"</script>'''

@app.route('/krishibhavan',methods=['post'])
def krishibhavan():
    place =request.form['textfield']
    post =request.form['textfield2']
    pin=request.form['textfield3']
    district = request.form['textfield4']
    phone = request.form['textfield5']
    uname = request.form['textfield6']
    password= request.form['textfield7']
    qry="INSERT INTO login VALUES(NULL,%s,%s,'krishibhavan')"
    val1=(uname,password)
    lid=iud(qry,val1)
    qry1="INSERT INTO `krishibhavan` VALUES(NULL,%s,%s,%s,%s,%s,%s)"
    val=(str(lid),place,post,pin,district,phone)
    iud(qry1,val)
    return '''<script>alert("NEW KRISHIBHAVAN ADDED...");window.location='/MANAGE_KRISHIBAVAN_DETAIL'</script>'''




@app.route('/ADD_KRISHIBAVAN_DETAILS', methods=['post'])
@login_required
def ADD_KRISHIBAVAN_DETAILS():
    return render_template('admin/ADD_KRISHIBAVAN-DETAILS.html')

@app.route('/ADMIN_HOME')
@login_required
def ADMIN_HOME():
    return render_template('admin/ADMIN_HOME.html')

@app.route('/MANAGE_KRISHIBAVAN_DETAIL')
@login_required
def MANAGE_KRISHIBAVAN_DETAIL():
    qry="SELECT * FROM krishibhavan"
    res=selectall(qry)
    return render_template('admin/MANAGE_KRISHIBAVAN-DETAIL.html',val=res)


@app.route('/REPLAY')
@login_required
def REPLAY():
    return render_template('admin/REPLAY.html')

@app.route('/UPLOAD_NEWS_VEDIO')
@login_required
def UPLOAD_NEWS_VEDIO():
    return render_template('admin/UPLOAD_NEWS-VEDIO.html')

@app.route('/UPLOAD_NEWS_VEDIO1',methods=['post'])
def UPLOAD_NEWS_VEDIO1():
    upload=request.files['file']
    dis=request.form['textfield']
    up=secure_filename(upload.filename)
    upload.save(os.path.join("static/news_tech",up))
    qry="INSERT INTO `upload_news` VALUES(NULL,%s,%s,curdate())"
    val=(up,dis)
    iud(qry,val)
    return'''<script>alert(" UPLOADED SUCCESSFULLY...");window.location="/UPLOAD_NEWS_VEDIO"</script>'''

@app.route('/UPLOAD_NEWS_VEDIO_TECH_PEOPLE')
@login_required
def UPLOAD_NEWS_VEDIO_TECH_PEOPLE():
    return render_template('admin/UPLOAD_NEWS-VEDIO_TECH-PEOPLE.html')

@app.route('/UPLOAD_NEWS_VEDIO_TECH_PEOPLE1',methods=['post'])
def UPLOAD_NEWS_VEDIO_TECH_PEOPLE1():
    upload=request.files['file']
    dis=request.form['textarea']
    up=secure_filename(upload.filename)
    upload.save(os.path.join("static/video",up))
    qry="INSERT INTO `upload_post` VALUES(NULL, %s, %s, CURDATE())"
    val=(up,dis)
    iud(qry,val)
    return'''<script>alert("Uploaded succesfully");window.location="/UPLOAD_NEWS_VEDIO_TECH_PEOPLE"</script>'''


@app.route('/VIEW_FEEDBACK')
@login_required
def VIEW_FEEDBACK():
    qry="SELECT `shop`.`Shop_Name`,`feedback`.`Feedback`,`Date` FROM `feedback` JOIN `shop` ON `shop`.`Login_ID`=`feedback`.`From_ID`"
    res=selectall(qry)
    return render_template('admin/VIEW_FEEDBACK.html',v=res)

@app.route('/VIEW_FEEDBACK1',methods=['post'])
@login_required
def VIEW_FEEDBACK1():
    qry="SELECT `farmer`.`First_Name`,`farmer`.`Last_Name`,`feedback`.* FROM farmer JOIN `feedback` ON `farmer`.`Login_ID`=`feedback`.`From_ID` JOIN `login` ON `feedback`.`From_ID`=`login`.`Login_ID` WHERE `login`.`Type`='krishibhavan' AND `login`.`Login_ID`!=%s"
    res=selectall2(qry,id)
    return render_template("admin/VIEW_FEEDBACK.html",val=res)

@app.route('/ADD_NOTIFICATION')
@login_required
def ADD_NOTIFICATION():
    return render_template('krishibhavan/ADD_NOTIFICATION.html')
@app.route('/add_notification',methods=['post'])
def add_notification():
    notification=request.form['textfield']
    qry="INSERT INTO `notification` VALUES(NULL,%s,CURDATE())"
    val=(notification)
    iud(qry,val)
    return '''<script>alert(" NOTIFICATION ADD SUCCESSFULLY...!!!");window.location="/ADD_NOTIFICATION"</script>'''


@app.route('/ADD_AGRI_EQUIPMENTS',methods=['post'])
@login_required
def ADD_AGRI_EQUIPMENTS():
    return render_template('krishibhavan/ADD_AGRI-EQUIPMENTS.html')

@app.route('/add_agri_equipments',methods=['post'])
def add_agri_equipments():
    name=request.form['textfield']
    image=request.files['file']
    im= secure_filename(image.filename)
    image.save(os.path.join("static/agri_equipments",im))
    description=request.form['textarea']
    price=request.form['textfield2']
    qry="INSERT INTO `agricultural_equipment` VALUES(NULL,%s,%s,%s,%s)"
    val=(name,im,description,price)
    iud(qry,val)
    return '''<script>alert("PRODUCT ADDED SUCCESFULLY...");window.location="/MANAGE_AGRI_EQUIPMENTS#services"</script>'''
    

@app.route('/APPROVE_REJECT_FARMER')
@login_required
def APPROVE_REJECT_FARMER():
    qry = "SELECT `farmer`.* FROM `farmer` JOIN `login` ON `farmer`.`Login_ID`=`login`.`login_ID` WHERE `login`.`Type`='pending'"
    res = selectall(qry)
    return render_template('krishibhavan/APPROVE-REJECT FARMER.html',v=res)

@app.route('/approve_farmer')
def approve_farmer():
    id = request.args.get('id')
    qry="UPDATE `login` SET `Type`='farmer' WHERE `login_ID`=%s "
    res=iud(qry,id)
    return '''<script>alert("APROVED...");window.location="/APPROVE_REJECT_FARMER"</script>'''

@app.route('/reject_farmer')
def reject_farmer():
    id = request.args.get('id')
    qry = "UPDATE `login` SET `Type`='rejected' WHERE `login_ID`=%s "
    res = iud(qry, id)
    return '''<script>alert("REJECTED...");window.location="/APPROVE_REJECT_FARMER"</script>'''

@app.route('/KRISHIBHAVAN_HOME')
@login_required
def KRISHIBHAVAN_HOME():
    return render_template('krishibhavan/KRISHIBHAVAN_HOME.html')

@app.route('/MANAGE_AGRI_EQUIPMENTS',methods=['post','get'])
@login_required
def MANAGE_AGRI_EQUIPMENTS():
    qry="SELECT * FROM `agricultural_equipment`"
    # val=(session['lid'])
    # res=selectall2(qry,val)
    res=selectall(qry)
    return render_template('krishibhavan/MANAGE_AGRI-EQUIPMENTS.html',v=res)

@app.route('/VERIFY_FARMER')
@login_required
def VERIFY_FARMER():
    return render_template('krishibhavan/VERIFY_FARMER.html')

@app.route('/VIEW_COMPLAINS_SEND_REPLAY')
@login_required
def VIEW_COMPLAINS_SEND_REPLAY():
    qry="SELECT `shop`.`Shop_Name`,`farmer`.`First_Name`,`complaint`.`Complaint`,`complaint`.`Date`,`complaint`.`Complaint_ID` FROM `farmer` JOIN `complaint` ON `farmer`.`Login_ID`=`complaint`.`Product_ID` JOIN `shop` ON `complaint`.`Shop_ID`=`shop`.`Login_ID` where `complaint`.`Reply`='pending'"
    res=selectall(qry)
    return render_template('krishibhavan/VIEW_COMPLAINS-SEND_REPLAY.HTML',v=res)

@app.route('/SENT_REPLAY_SHOP')
@login_required
def SENT_REPLAY_SHOP():
    id = request.args.get('id')
    session['id']=id
    return render_template('krishibhavan/REPLAY.html')

@app.route('/sent_replay_shop',methods=['post'])
@login_required
def sent_replay_shop():
    reply=request.form['text']
    qry="UPDATE `complaint` SET `Reply`=%s WHERE `Complaint_ID`=%s "
    val=(reply,session['id'])
    iud(qry,val)
    return'''<script>alert("SENDED!!!");window.location="VIEW_COMPLAINS_SEND_REPLAY#services"</script>'''


@app.route('/K_VIEW_FEEDBACK')
@login_required
def K_VIEW_FEEDBACK():
    return render_template('krishibhavan/VIEW_FEEDBACK.html')

@app.route('/VIEW_PRODUCT_REQUEST')
@login_required
def VIEW_PRODUCT_REQUEST():
    qry="SELECT `agricultural_equipment`.`Name`,`farmer`.`First_Name`,`farmer`.`Last_Name`,`order_equipment`.`Date`,`order_equipment`.`quantity` FROM `agricultural_equipment` JOIN `order_equipment` ON `order_equipment`.`Equipment_ID`=`agricultural_equipment`.`Equipment_ID` JOIN `farmer` ON `farmer`.`Login_ID`=`order_equipment`.`Farmer_ID`"
    res=selectall(qry)
    return render_template('krishibhavan/VIEW_PRODUCT-REQUEST.html',v=res)

@app.route('/PAY_AMOUNT')
@login_required
def PAY_AMOUNT():
    total = session['price']
    print(total)
    return render_template('shop/PAY_AMOUNT.html',tot=total)
@app.route('/pay',methods=['post'])
def pay():
    Account_No = request.form['accno']
    ifsc = request.form['ifsc']
    key = request.form['key']
    shopid = session['lid']
    total=session['price']
    qry = "SELECT * FROM `bank` WHERE `Account_No`=%s AND `IFSC`=%s AND`Key`=%s and farmer_id=%s"
    val = (Account_No, ifsc, key, shopid)
    res = selectone(qry, val)
    if res is None:

        return '''<script>alert("Invalid account!!!");window.location="/VIEW_ORDER"</script>'''
    else:
        orgamt = int(res[4])
        if orgamt > int(total):
            qry = "UPDATE `bank` SET `Amount`=Amount-%s WHERE `Farmer_ID`=%s"
            val = (total, shopid)
            iud(qry, val)
            qry = "UPDATE `bill` SET `Status`='payed' WHERE `Bill_ID`=%s"
            val = (session['id'])
            iud(qry, val)
            # qry="select * from `order_equipment` WHERE `Bill_Equipment_ID`=%s"
            # res=selectall2(qry,bil_equ_lid)
            # for i in res:
            #     qry="update "

            return '''<script>alert("payment success!!!");window.location="/VIEW_ORDER"</script>'''
        else:
            return '''<script>alert("insufficient amount!!!");window.location="/VIEW_ORDER"</script>'''

@app.route('/payment_product_shop')
@login_required
def payment_product_shop():
    return render_template('shop/payment-product-shop.html')
@app.route('/PURCHASE_AGRI_MATERIALS')
@login_required
def PURCHASE_AGRI_MATERIALS():
    return render_template('shop/PURCHASE_AGRI-MATERIALS.html')
@app.route('/SHOP_REG_FORM')
def SHOP_REG_FORM():
    return render_template('shop/SHOP_REG-FORM.html')
@app.route('/VIEW_REPLAY')
@login_required
def VIEW_REPLAY():
    qry="SELECT `complaint`.`Complaint`,`complaint`.`Reply`,`complaint`.`Date` FROM `complaint` WHERE `Shop_ID`=%s"
    val=(session['lid'])

    res=selectall2(qry,val)
    return render_template('shop/VIEW REPLAY.html',v=res)

@app.route('/VIEW_PRODUCT')
@login_required
def VIEW_PRODUCT():
    qry="SELECT * FROM `product` "
    res=selectall(qry)
    return render_template('shop/VIEW_PRODUCT.html',v=res)
@app.route('/PRODUCT_REQUEST')
@login_required
def PRODUCT_REQUEST():
    qry="SELECT * FROM `product`"
    res=selectall(qry)
    return render_template('shop/PRODUCT_REQUEST.html',v=res)
@app.route('/VIEW_ORDER')
@login_required
def VIEW_ORDER():
    qry="SELECT `bill`.* FROM `bill` WHERE `Shop_ID`=%s"
    val=(session['lid'])
    res=selectall2(qry,val)
    # qry="SELECT `product`.`Product_Name`,`product`.`Quantity`,`product`.`Price`,`order_product`.`Date` FROM `product` JOIN `order_product` ON `product`.`Product_ID`=`order_product`.`Product_ID`"
    # res=selectall(qry)
    return render_template('shop/VIEW_ORDER.html',v=res)
@app.route('/book_now')
@login_required
def book_now():
    bid=request.args.get('id')
    qry="UPDATE `bill` SET `Status`='pending' WHERE `Bill_ID`=%s"
    val=(bid)
    iud(qry,val)
    return redirect('/VIEW_ORDER')

@app.route('/payment')
@login_required
def payment():
    bid=request.args.get('id')
    session['id']=bid
    price = request.args.get('price')
    session['price']=price
    return render_template('shop/PAY_AMOUNT.html',tot=price)


@app.route('/order')
@login_required
def order():
    pid=request.args.get('id')
    p=request.args.get('p')
    session['pid']=pid
    session['p']=p
    return render_template("shop/PRODUCT_REQUEST1.html")
@app.route('/PRODUCT_REQUEST1',methods=['post'])
def PRODUCT_REQUEST1():

    pid=session['pid']
    qty=request.form['qty']

    qry="SELECT * FROM `bill` WHERE `Shop_ID`=%s AND `Status`='cart'"
    val=(session['lid'])
    res=selectone(qry,val)
    bid=0
    if res is None:
        qry="INSERT INTO `bill`VALUES(NULL,%s,CURDATE(),0,'cart')"
        bid=iud(qry,val)
    else:
        bid=res[0]
    qry="INSERT INTO `order_product`VALUES(NULL,%s,%s,CURDATE(),%s,%s)"
    val=(pid,session['lid'],qty,bid)
    iud(qry,val)
    tp=int(session['p'])*int(qty)

    qry="UPDATE `bill` SET `Total_Price`=`Total_Price`+"+str(tp)+" WHERE `Bill_ID`=%s"
    val=(bid)

    iud(qry,val)

    return redirect('/PRODUCT_REQUEST')





@app.route('/VIEW_NOTIFICATION_SHOP')
@login_required
def VIEW_NOTIFICATION_SHOP():
    qry="SELECT * FROM `notification`"
    res=selectall(qry)
    return render_template('shop/VIEW_NOTIFICATION_SHOP.html',v=res)

@app.route('/ADD_FEEDBACK')
@login_required
def ADD_FEEDBACK():
    return render_template('shop/ADD_FEEDBACK.html')

@app.route('/add_feedback',methods=['post'])
def add_feedback():
    u_id=session['lid']
    qry1="SELECT `login`.`login_ID` FROM `login` WHERE `Type`='admin'"
    a_id=selectall(qry1)
    feedback=request.form['textarea']
    qry = "INSERT INTO `feedback`  VALUES(NULL,%s,%s,%s,CURDATE())"
    val=(u_id,a_id,feedback)
    iud(qry,val)
    return'''<script>alert("FEEDBACK ADDED SUCCESFULLY");window.location="SHOP_HOME"</script>'''
@app.route('/POST_COMPLAINT')
@login_required
def POST_COMPLAINT():
    qry="SELECT `farmer`.`Login_ID`,`farmer`.`First_Name`,`farmer`.`Last_Name` FROM `farmer`"
    res=selectall(qry)
    return render_template('shop/POST_COMPLAINT.html',v=res)

@app.route('/post_complaints',methods=['post'])
def post_complaints():
    u_id = session['lid']
    fid=request.form['f']
    complaints= request.form['textarea']
    qry="INSERT INTO `complaint`  VALUES(NULL,%s,%s,%s,CURDATE(),'pending')"
    val = (fid,u_id,complaints)
    iud(qry, val)
    return'''<script>alert("COMPLAINTS ADDED SUSSESFULLY!!!");window.location="SHOP_HOME"</script>'''

@app.route('/EDIT_KRISHIBAVAN_DETAILS')
@login_required
def EDIT_KRISHIBAVAN_DETAILS():
    id = request.args.get('id')
    session['kid']=id
    qry="SELECT * FROM `krishibhavan` WHERE Krishibhavan_ID=%s"
    val=(id)
    res=selectone(qry,val)
    return render_template('admin/EDIT_KRISHIBAVAN-DETAILS.html',v=res)

@app.route('/edit_kb_details',methods=['post'])
def edit_kb_details():
    place = request.form['textfield']
    post = request.form['textfield2']
    pin = request.form['textfield3']
    district = request.form['textfield4']
    phone = request.form['textfield5']
    qry="UPDATE `krishibhavan` SET `PLace`=%s,`Post`=%s,`Pin`=%s,`District`=%s,`Phone`=%s WHERE `Krishibhavan_ID`=%s"
    val=(place, post, pin, district, phone,session['kid'])
    iud(qry,val)
    return '''<script>alert("EDITED!!!");window.location="/MANAGE_KRISHIBAVAN_DETAIL#services"</script>'''

@app.route('/delete_kb_details')
def delete_kb_details():
    id = request.args.get('id')
    qry="DELETE FROM `krishibhavan`WHERE `Krishibhavan_ID`=%s"
    iud(qry,id)
    return '''<script>alert("DELETED!!!");window.location="/MANAGE_KRISHIBAVAN_DETAIL#services"</script>'''
@app.route('/EDIT_AGRI_EQUPMENTS')
@login_required
def EDIT_AGRI_EQUPMENTS():
    id = request.args.get('id')
    session['agri_id']=id
    qry="SELECT * FROM `agricultural_equipment` WHERE `Equipment_ID`=%s"
    val=(id)
    res=selectone(qry,val)
    return render_template('krishibhavan/EDIT_AGRI_EQUIPMENTS.html',v=res)

@app.route('/edit_agri_equpments',methods=['post'])
@login_required
def edit_agri_equpments():
    try:
        name = request.form['textfield']
        image = request.files['file']
        im = secure_filename(image.filename)
        image.save(os.path.join("static/agri_equipments",im))
        discription = request.form['textarea']
        price = request.form['textfield2']
        qry="UPDATE `agricultural_equipment`SET `Name`=%s,`Image`=%s,`Description`=%s,`Price`=%s WHERE `Equipment_ID`=%s"
        val=(name ,im,discription,price,session['agri_id'])
        iud(qry,val)
    except Exception as e:
        name = request.form['textfield']
        discription = request.form['textarea']
        price = request.form['textfield2']
        qry = "UPDATE `agricultural_equipment`SET `Name`=%s,`Description`=%s,`Price`=%s WHERE `Equipment_ID`=%s"
        val = (name,discription, price, session['agri_id'])
        iud(qry, val)

    return '''<script>alert("EDITED!!!");window.location="/MANAGE_AGRI_EQUIPMENTS#services"</script>'''

@app.route('/delete_agri_equpment')
def delete_agri_equpment():
    id = request.args.get('id')
    print(id)
    qry="DELETE FROM `agricultural_equipment`WHERE `Equipment_ID`=%s"
    iud(qry,id)
    return '''<script>alert("DELETED!!!");window.location="/MANAGE_AGRI_EQUIPMENTS#services"</script>'''


@app.route('/SHOP_REG_FORM1',methods=['post'])
def SHOP_REG_FORM1():
    Shop_Name=request.form['textfield']
    Place=request.form['textfield2']
    Post=request.form['textfield3']
    Pin=request.form['textfield4']
    District=request.form['textfield5']
    Phone=request.form['textfield6']
    Email=request.form['textfield7']
    User_Name=request.form['textfield8']
    Password=request.form['password']
    qry1 = "INSERT INTO `login` VALUES(NULL, %s,%s,'shop')"
    val1 = (User_Name, Password)
    res=iud(qry1,val1)
    qry="INSERT INTO `shop` VALUES(NULL,%s,%s,%s,%s,%s,%s,%s,%s)"
    val=( str(res),Shop_Name,Place,Post, Pin,District,Phone, Email)
    iud(qry,val)
    return '''<script>alert("REGISTERED!!!");window.location="SHOP_HOME"</script>'''

@app.route('/SHOP_HOME')
@login_required
def SHOP_HOME():
    return render_template('shop/SHOP_HOME.html')
@app.route('/')
def index1():
    return render_template("index.html")

@app.route('/index')
def index():
    return render_template('main_index.html')
@app.route('/VIEW_news')
@login_required
def VIEW_news():
    qry="SELECT * FROM upload_news"
    res=selectall(qry)
    # qry="SELECT `product`.`Product_Name`,`product`.`Quantity`,`product`.`Price`,`order_product`.`Date` FROM `product` JOIN `order_product` ON `product`.`Product_ID`=`order_product`.`Product_ID`"
    # res=selectall(qry)
    return render_template('shop/View_news.html',v=res)


@app.route('/VIEW_post')
@login_required
def VIEW_post():
    qry="SELECT * FROM upload_post"
    res=selectall(qry)
    # qry="SELECT `product`.`Product_Name`,`product`.`Quantity`,`product`.`Price`,`order_product`.`Date` FROM `product` JOIN `order_product` ON `product`.`Product_ID`=`order_product`.`Product_ID`"
    # res=selectall(qry)
    return render_template('shop/View_post.html',v=res)

@app.route('/logout')
def logout():
    session.clear()
    return render_template('login.html')

app.run(debug=True)





