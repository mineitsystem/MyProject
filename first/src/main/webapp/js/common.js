//-----------------------------------------------------------------------------
//배열원소추가
//@param :  Object
//@return : void
//-----------------------------------------------------------------------------
Array.prototype.add=function(o){
	this.push(o);
};

//-----------------------------------------------------------------------------
//배열원소삭제
//@param : 인덱스
//@return : Array
//-----------------------------------------------------------------------------
Array.prototype.remove=function(ind){
	var a=[];
	var c=[];
	if(ind){
		for(var i=0;i<this.length;i++)
			if(i!=ind)a.push(this[i]);
		c=a;
	} else {
		this.pop();
		c=this;
	}
	return c;
};

//-----------------------------------------------------------------------------
//배열원소크기
//@param :  void
//@return : 배열원소의 크기.
//-----------------------------------------------------------------------------
Array.prototype.size=function(){return this.length;};

//-----------------------------------------------------------------------------
//인덱스에해당하는 배열원소의 값을 얻어온다.
//@param :  인덱스
//@return : Object
//-----------------------------------------------------------------------------
Array.prototype.get=function(ind){
	if(typeof(ind)=="number" && ind>this.size()-1)
		return "";

	if(this[ind] == null) return "";

	return this[ind];
};
//-----------------------------------------------------------------------------
// 배열원소의 첫번째값을 얻어온다.
//@param :  void
//@return : Object
//-----------------------------------------------------------------------------
Array.prototype.fget=function(){if(this.size()==0)return ""; return this[0];};

//-----------------------------------------------------------------------------
//배열원소의 마지막값을 얻어온다.
//@param :  void
//@return : Object
//-----------------------------------------------------------------------------
Array.prototype.lget=function(){if(this.size()==0)return ""; return this[this.size()-1];};

//-----------------------------------------------------------------------------
//배열을 FROM인덱스 부터 TO인덱스까지 잘라준다.
// TO인덱스 가없다면   FROM인덱스 부터 마지막까지 잘라준다.
//@param :  FROM인덱스, TO인덱스
//@return : Array
//-----------------------------------------------------------------------------
Array.prototype.part=function(f,t){
	if(this.part.arguments.length==1)
		return this.slice(this.part.arguments[0]);
	else
		return this.slice(this.part.arguments[0],this.part.arguments[1]);
};

//-----------------------------------------------------------------------------
//배열을 key, value 식으로 원소를 저장한다. 배열의 index는 안생김.
//@param :  key, value
//@return : void
//-----------------------------------------------------------------------------
Array.prototype.put=function(o,v){this[o]=v;};

//-----------------------------------------------------------------------------
// key, value 식으로 저장한 배열의 크기를 가져온다.
//@param :  void
//@return :  key, value 식으로 저장한 배열의크기
//-----------------------------------------------------------------------------
Array.prototype.msize=function(){
	var r=0;
	for(var key in this)
		if(typeof key=="string" && typeof this[key]!="function")
			r++;
	return r;
};


//-----------------------------------------------------------------------------
//key, value 식으로 저장한 배열의 키 중복체크.
//@param :  key
//@return : key, value 식으로 저장한 배열에 매개변수키가 존재하면 true를 없으면 false를 리턴한다.
//-----------------------------------------------------------------------------
Array.prototype.containsKey=function(k){
	for(var key in this) {
		if(typeof key=="string" && typeof this[key]!="function" && key==k)
			return !0;
	}
	return !1;
};


//-----------------------------------------------------------------------------
//key, value 식으로 저장한 배열의 값 중복체크.
//@param :  key
//@return : key, value 식으로 저장한 배열에 매개변수값이 존재하면 true를 없으면 false를 리턴한다.
//-----------------------------------------------------------------------------
Array.prototype.containsValue=function(v){
	for(var key in this) {
		if(typeof key=="string"&& typeof this[key]!="function" && this[key] == v)
			return !0;
		}
	return !1;
};


//-----------------------------------------------------------------------------
// 배열 > json 변환.
//@param :  cname
//@return : json
//-----------------------------------------------------------------------------
Array.prototype.jsof=function(cname){
	return eval("("+this.jsontt(cname)+")");
};

//-----------------------------------------------------------------------------
//배열 > json문자열 변환. 랩퍼함수.
//@param :  cname
//@return : json 문자열
//-----------------------------------------------------------------------------
Array.prototype.jsos=function(cname){
	return this.jsontt(cname);
};

//-----------------------------------------------------------------------------
//배열 > json문자열 변환.
//@param :  cname
//@return : json 문자열
//-----------------------------------------------------------------------------
Array.prototype.jsontt=function(cname){
	var __jsos = "";
    if(cname) __jsos="{"+cname+":["; else __jsos="[";
    if(this.size() > 0)
        for(var i=0; i<this.size();i++) {
            var c = this.get(i);
            if(c.msize() > 0) {
                __jsos += "{_sid:\"s_"+i+"\"";
                for(var key in c) {
                	if(typeof(key)=="string" && typeof(c[key]) !="function") {

                		key = key.replace(/\"/g,'\\"');
                		var val = c[key];
                		val = val.replace(/\"/g,'\\"');

                		__jsos +=  ","+key+":\""+val+"\"";
                	}
                }
                if(i == this.size()-1) __jsos += "}"; else __jsos += "},";
            }
        };
    if(cname) __jsos += "]}"; else __jsos += "]";
    return  __jsos;
};

//-----------------------------------------------------------------------------
// 문자중에 정규표현식 문자열 치환.
//@param :  void
//@return : 치환문자열.
//-----------------------------------------------------------------------------
String.prototype.meta = function() {
    var str = this;
    var result = "";

    for ( var i = 0; i < str.length; i++) {
        if ((/([\$\(\)\*\+\.\[\]\?\\\^\{\}\|]{1})/).test(str.charAt(i))) {
            result += str.charAt(i).replace((/([\$\(\)\*\+\.\[\]\?\\\^\{\}\|]{1})/), "\\$1");
        } else {
            result += str.charAt(i);
        }
    }
    return result;
};

//-----------------------------------------------------------------------------
// 문자중에 원하는문자열  ""으로치환.
//@param :  치환문자열
//@return : 치환된문자열.
//@ex : "abcdbebfbgb".remove("b")
//-----------------------------------------------------------------------------
String.prototype.remove=function(a){
	return a==null?this:eval("this.replace(/["+a.meta()+']/g, "")');
};

//-----------------------------------------------------------------------------
//   a <= 문자열의 크기 <= b
//@param :  From a, To b
//@return : a <= 문자열의 크기 <= b 문자열 true, 아니면 false
// b가 없으면 a보다 크거나 같으면 무조건 참.
//@ex : "abcdbebfbgb".isLength(3,6)
//      "abcdbebfbgb".isLength(3)
//-----------------------------------------------------------------------------
String.prototype.isLength=function(a,b){
	var c=b?b:null,d=!0;
	this.length<a&&(d=!1);
	c&&this.length>c&&(d=!1);
	return d;
};

//-----------------------------------------------------------------------------
//문자열이 비어있는지 체크.
//@param :  void
//@return : 비어있으면 true 문자열이 존재하면 false
//@ex : "abcdbebfbgb".isBlank()
//-----------------------------------------------------------------------------
String.prototype.isBlank=function(){
	for(var a=this.trim(),b=0;b<a.length;b++)
		if(a.charAt(b)!="\t"&&a.charAt(b)!="\n"&&a.charAt(b)!="\r")
			return!1;
	return!0;
};

//-----------------------------------------------------------------------------
// 문자열이 숫자로변환가능 여부.
//@param :  문자열a
//@return : 문자열 a 를 제외하고   숫자로변환가능하면 true, 아니면 false
//@ex :        "ab12345".isNumeric("ab")
//             "12345".isNumeric()
//             "asasa".isNumeric()
//-----------------------------------------------------------------------------
String.prototype.isNumeric=function(a){
	return/^[0-9]+$/.test(this.remove(a))?!0:!1;
};

//-----------------------------------------------------------------------------
//한글여부 체크
//@return : boolean
//-----------------------------------------------------------------------------
String.prototype.isKorean = function() {
	return (/^[ㄱ-ㅎ가-힝]+$/).test(this.remove(arguments[0]))?!0:!1;
};

//알파벳여부
String.prototype.isAlpha=function(a){return/^[a-zA-Z]+$/.test(this.remove(a))?!0:!1;};

//알파벳+넘버 조합 체크.
String.prototype.isAlphaNumeric=function(a){return/^[0-9a-zA-Z]+$/.test(this.remove(a))?!0:!1;};

//-----------------------------------------------------------------------------
//날짜형으로 변환
//yyyy-mm-dd, yyyy-mm
//@return : String
//@ex : "20100305".toDate()   ==> 2010-03-05
//      "20100305".toDate("/")   ==> 2010/03/05
//-----------------------------------------------------------------------------
String.prototype.toDate=function(ch) {
	var data = this;
	data = data.replace(/-/g,"");

	if(ch == undefined) ch = "-";

	var mask = "9999"+ch+"99"+ch+"99";

	if(data.length < 8) {
		mask = "9999"+ch+"99";
	}

    return data.toMask(mask);
};

//-----------------------------------------------------------------------------
//Number형으로 변환 .
//@return : Number
//@ex : "1234".toInt()    ==> 1234
//      "1,234".toInt()   ==> 1234
//-----------------------------------------------------------------------------
String.prototype.toInt=function() {
	var data = this;
	if(data == null || data == "") return 0;
	data = data.replace(/,/g, "");
	if(isNaN(data)) return 0;
	return parseInt(data);
};

//-----------------------------------------------------------------------------
//금액으로 변환
//xx,xxx.xx
//@return : String
//	"1234".toMoney()   ==> 1,234
//	"12341234".toMoney()   ==> 12,341,234
//-----------------------------------------------------------------------------
String.prototype.toMoney=function() {

	var data = this;

	if(isNaN(data))
        return 0;

    data = data+"";

	data = Number(data);
	data = data.toString();

    var minus = false;

    if(data.indexOf("-") > -1)
        minus = true;

    var sMoney = data.replace(/(,|-)/g,"");
    var tMoney = "";
    var rMoney = "";
    var len = sMoney.length;

    if(sMoney.indexOf(".") >= 0) {	// 소숫점이 있을 경우
		var cMoney = sMoney.substr(0, sMoney.indexOf("."));
		var eMoney = sMoney.substr(sMoney.indexOf("."), sMoney.length);

		if(cMoney.length <= 3) {
			if(minus) cMoney = "-" + cMoney + eMoney;
			else cMoney = cMoney + eMoney;

			return cMoney;
		}
		for(i=0; i<cMoney.length; i++) {
			if(i != 0 && ( i % 3 == cMoney.length % 3) ) rMoney += ",";
			if(i < cMoney.length ) rMoney += cMoney.charAt(i);
		}
		if(minus) rMoney = "-" + rMoney + eMoney;
		else rMoney = rMoney + eMoney;

		return rMoney;
	}
    if ( sMoney.length <= 3 ) {
        if(minus) sMoney = "-"+sMoney;
        return sMoney;
    }
    for(i = 0; i < len; i++){
        if(i != 0 && ( i % 3 == len % 3) ) tMoney += ",";
        if(i < len ) tMoney += sMoney.charAt(i);
    }
    if(minus) tMoney = "-" + tMoney;

    return tMoney;

};

//-----------------------------------------------------------------------------
//mask 적용한 결과 반환
//@return : String
//@return : String
//    "20130405".toMask('9999/99/99')  ==> 2013/04/05
//    ( -,:, /, |)4개만 처리
//-----------------------------------------------------------------------------
String.prototype.toMask=function(mask) {
	var data = this;
	data = data.replace(/[^a-z|^A-Z|^\d]/g,'');
	var len = data.length;
	var result = '';
	var j = 0;

	for(var i = 0; i < len; i++){
	  result += data.charAt(i);
	  j++;
	  if (j < mask.length && '-:|/'.indexOf(mask.charAt(j)) != -1 ) result += mask.charAt(j++);
	}
	return result;
};

//-----------------------------------------------------------------------------
//전화번호로 변환
//xxxx-xxxx, xxx-xxxx
//@return : String
//-----------------------------------------------------------------------------
String.prototype.toTel=function() {
    var data = this;

    if(data == null)
        return "";
    var sTel = data.replace(/(\(|\)|-)/g,"");

    if(sTel.length <= 2) return sTel;

    var seoul = false;
    if(sTel.substr(0,2) == "02")
        seoul = true;

    var mask = "";
    var len = sTel.length;

    if(len <= 8) {
        if(len == 8)
            mask = "9999-9999";
        else
            mask = "999-9999";
    } else {
        if(seoul) {
            if(sTel.length == 9)
                mask = "99-999-9999";
            else
                mask = "99-9999-9999";
        } else {
            if(sTel.length == 10)
                mask = "999-999-9999";
            else if(sTel.length == 11)
                mask = "999-9999-9999";
            else
              mask = "9999-9999-9999";
        }
    }

    return sTel.toMask(mask);
};

//-----------------------------------------------------------------------------
//, 또는 - 제거
//-----------------------------------------------------------------------------
String.prototype.toUnFormat=function() {
	var data = this;
	return data.replace(/(,|-)/g,"");
};

//-----------------------------------------------------------------------------
//len만큼 문자열을 연결하여 반환
// "a".string(3);    ==> "aaa"
// "asdf".string(3); ==> "asdfasdfasdf"
//-----------------------------------------------------------------------------
String.prototype.string=function(len){
	var s = "", i = 0;
	while (i++ < len)
		s += this;
	return s;
};

//-----------------------------------------------------------------------------
//자릿수(len)에 맞춰 문자열 왼쪽에 0으로 채움
//@return : String
//-----------------------------------------------------------------------------
String.prototype.zpad=function(len){
	return "0".string(len - this.length) + this;
};

//-----------------------------------------------------------------------------
//공백제거
//-----------------------------------------------------------------------------
String.prototype.trim=function(){return this.replace(/(^\s*)|(\s*$)/g, "");};
String.prototype.rtrim=function(){return this.replace(/(\s*$)/, "");};
String.prototype.ltrim=function(){return this.replace(/(^\s*)/, "");};

//-----------------------------------------------------------------------------
//HTML태그제거
//-----------------------------------------------------------------------------
String.prototype.stripTags=function(){return this.replace(/<\/?[^>]+>/gi, '');};

//-----------------------------------------------------------------------------
//HTML태그제거
//-----------------------------------------------------------------------------
String.prototype.toByte=function() {
	var str = this;
	var len = 0;
	for(var i = 0; i < str.length; i++) {
		if(escape(str.charAt(i)).length >= 4)
			len += 2;
		else if(escape(str.charAt(i)) == "%A7")
			len += 2;
		else
			if(escape(str.charAt(i)) != "%0D")
				len++;
	}
	return len;
};

//-----------------------------------------------------------------------------
//주민등록번호 체크
//xxxxxx-xxxxxxx
//@return : boolean
//-----------------------------------------------------------------------------
String.prototype.isSSN = function() {
    var arg = arguments[0] ? arguments[0] : "";

    var ssn = eval("this.match(/[0-9]{2}[01]{1}[0-9]{1}[0123]{1}[0-9]{1}" + arg + "[1234]{1}[0-9]{6}$/)");

    if (ssn == null) {
        return false;
    } else {
        ssn = ssn.toString().num().toString();
    }

    // 생년월일 체크
    var birthYY = (parseInt(ssn.charAt(6)) == (1 || 2)) ? "19" : "20";

    birthYY += ssn.substr(0, 2);

    var birthMM = ssn.substr(2, 2) - 1;
    var birthDD = ssn.substr(4, 2);
    var birthDay = new Date(birthYY, birthMM, birthDD);

    if (birthDay.getFullYear() % 100 != ssn.substr(0, 2) || birthDay.getMonth() != birthMM || birthDay.getDate() != birthDD) {

        return false;
    }

    var sum = 0;
    var num = [ 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5 ];
    var last = parseInt(ssn.charAt(12));

    for ( var i = 0; i < 12; i++) {
        sum += parseInt(ssn.charAt(i)) * num[i];
    }

    return ((11 - sum % 11) % 10 == last) ? true : false;
};

//-----------------------------------------------------------------------------
//사업자번호 체크 - arguments[0] : 등록번호 구분자
//XX-XXX-XXXXX
//@return : boolean
//-----------------------------------------------------------------------------
String.prototype.isBizNum = function() {
 var arg = arguments[0] ? arguments[0] : "";
 var biznum = eval("this.match(/[0-9]{3}" + arg + "[0-9]{2}" + arg + "[0-9]{5}$/)");

 if (biznum == null) {
     return false;
 } else {
     biznum = biznum.toString().num().toString();
 }

 var sum = parseInt(biznum.charAt(0));
 var num = [ 0, 3, 7, 1, 3, 7, 1, 3 ];

 for ( var i = 1; i < 8; i++) {
     sum += (parseInt(biznum.charAt(i)) * num[i]) % 10;
 }

 sum += Math.floor(parseInt(parseInt(biznum.charAt(8))) * 5 / 10);
 sum += (parseInt(biznum.charAt(8)) * 5) % 10 + parseInt(biznum.charAt(9));

 return (sum % 10 == 0) ? true : false;
};

//------------------------------------------------------------------------------
//법인 등록번호 체크 - arguments[0] : 등록번호 구분자
//XXXXXX-XXXXXXX
//@return : boolean
//------------------------------------------------------------------------------
String.prototype.isCorpNum = function() {
 var arg = arguments[0] ? arguments[0] : "";
 var corpnum = eval("this.match(/[0-9]{6}" + arg + "[0-9]{7}$/)");

 if (corpnum == null) {
     return false;
 } else {
     corpnum = corpnum.toString().num().toString();
 }

 var sum = 0;
 var num = [ 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 ];
 var last = parseInt(corpnum.charAt(12));

 for ( var i = 0; i < 12; i++) {
     sum += parseInt(corpnum.charAt(i)) * num[i];
 }

 return ((10 - sum % 10) % 10 == last) ? true : false;
};

//------------------------------------------------------------------------------
// Byte크기
//@return : Byte크기
//------------------------------------------------------------------------------
String.prototype.cutByte=function(len, disp) {
	var str = this;
	var count = 0;
	for(var i = 0; i < str.length; i++) {
		if(escape(str.charAt(i)).length >= 4)
			count += 2;
		else
			if(escape(str.charAt(i)) != "%0D")
				count++;
		if(count >  len) {
			if(escape(str.charAt(i)) == "%0A")
				i--;
			break;
		}
	}
	var dispStr = str.substring(0, i);

	if(this.toByte() > len) {
		dispStr = dispStr + disp;
	}

	return dispStr;
};

//-----------------------------------------------------------------------------
//현재날짜 취득
//@param  : 날짜 구분자('-', '/' etc)
//@return : String
//@ex : Date.today()     ==> 20100305
//      Date.today('-')  ==> 2010-03-05
//      Date.today('/')  ==> 2010/03/05
//-----------------------------------------------------------------------------
Date.prototype.today = function(ch)
{
    var yyyy = this.getFullYear().toString();
    var mm = (this.getMonth() + 1).toString();
    var dd = this.getDate().toString();

    var yyyymmdd = yyyy + (mm[1] ? mm : '0'+mm[0]) + (dd[1] ? dd : '0'+dd[0]);
    
    return yyyymmdd.toDate(ch);
}

//------------------------------------------------------------------------------
//모달리스팝업
//@param : 경로, 팝업이름, 넓이, 높이, 위치left, 위치top
//@return : void
//------------------------------------------------------------------------------
function openWin(loc,popname,width,height,left,top) {
    var args = openWin.arguments; 
    if(args.length < 3) {
        width = document.body.clientWidth / 2;
        height = document.body.clientHeight / 2;
    }
    if(args.length < 5) {
        left = (screen.width - width) / 2;
        top = (screen.height - height) / 2;
    }
    var status = "toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=yes,resizable=0,width=" + width + ",height=" + height + ",top=" + top + ",left=" + left;
    openw = window.open(loc,popname,status);
}

//------------------------------------------------------------------------------
//모달팝업
//@param : 경로, 팝업전달객체, 넓이, 높이
//@return : void
//------------------------------------------------------------------------------
function openPop(loc, args, width, height) {

    if(!args)
        args = {};

    var ret = window.showModalDialog(loc, args, "dialogWidth:"+width+"px;dialogHeight:"+height+"px;scroll:no;status:no;location:no;");
    args.returnValue = ret;

    return args;
}


function addClass(elements, myClass) {

	  // if there are no elements, we're done
	  if (!elements) { return; }

	  // if we have a selector, get the chosen elements
	  if (typeof(elements) === 'string') {
	    elements = document.querySelectorAll(elements);
	  }

	  // if we have a single DOM element, make it an array to simplify behavior
	  else if (elements.tagName) { elements=[elements]; }

	  // add class to all chosen elements
	  for (var i=0; i<elements.length; i++) {

	    // if class is not already found
	    if ( (' '+elements[i].className+' ').indexOf(' '+myClass+' ') < 0 ) {

	      // add class
	      elements[i].className += ' ' + myClass;
	    }
	  }
}

function removeClass(selector, myClass) {

	  // get all elements that match our selector
	  elements = document.querySelectorAll(selector);

	  // remove class from all chosen elements
	  for (var i=0; i<elements.length; i++) {
	    elements[i].classList.remove(myClass);
	  }
}


function modalAlert(title,body,state){
	
	var modalState = "alert-";
	
	switch(state){
		case 1:
			modalState = modalState+"success";
			break;
		case 2:
			modalState = modalState+"info";
			break;
		case 3:
			modalState = modalState+"warning";
			break;
		case 4:
			modalState = modalState+"danger";
			break;
		default:
			modalState = modalState+"info";
	
	}
	
	document.getElementById("modal_title").innerHTML = title;
	document.getElementById("modal_body").innerHTML = body;		
	addClass(document.getElementsByClassName("modal-header")[0],modalState);
	document.getElementsByClassName("modal-content")[0].style.width = "60%";
	document.getElementsByClassName("modal-content")[0].style.marginLeft = "20%";	
	
	$("#modal_content").modal('show');
}

function gfn_isNull(str) {
    if (str == null) return true;
    if (str == "NaN") return true;
    if (new String(str).valueOf() == "undefined") return true;   
    var chkStr = new String(str);
    if( chkStr.valueOf() == "undefined" ) return true;
    if (chkStr == null) return true;   
    if (chkStr.toString().length == 0 ) return true;  
    return false;
}
 
function ComSubmit(opt_formId) {
    this.formId = gfn_isNull(opt_formId) == true ? "commonForm" : opt_formId;
    this.url = "";
     
    if(this.formId == "commonForm"){    	
        $("#commonForm")[0].reset();
    }
     
    this.setUrl = function setUrl(url){
        this.url = url;
    };
     
    this.addParam = function addParam(key, value){    	
        $("#"+this.formId).html($("<input type='hidden' name='"+key+"' id='"+key+"' value='"+value+"' >"));
    };
     
    this.submit = function submit(){
        var frm = $("#"+this.formId)[0];
        frm.action = this.url;
        frm.method = "post";
        frm.submit();  
    };
}

var gfv_ajaxCallback = "";

function ComAjax(opt_formId){
    this.url = "";     
    this.formId = gfn_isNull(opt_formId) == true ? "commonForm" : opt_formId;
    this.param = "";
     
    if(this.formId == "commonForm"){
        var frm = $("#commonForm");
        if(frm.length > 0){
            frm.remove();
        }
        var str = "<form id='commonForm' name='commonForm'></form>";
        $('body').append(str);
    }
     
    this.setUrl = function setUrl(url){
        this.url = url;
    };
     
    this.setCallback = function setCallback(callBack){
        fv_ajaxCallback = callBack;
    };
 
    this.addParam = function addParam(key,value){
        this.param = this.param + "&" + key + "=" + value;
    };
     
    this.ajax = function ajax(){
        if(this.formId != "commonForm"){
            this.param += "&" + $("#" + this.formId).serialize();
        }
        $.ajax({
            url : this.url,   
            type : "POST",  
            data : this.param,
            async : false,
            success : function(data, status) {
                if(typeof(fv_ajaxCallback) == "function"){
                    fv_ajaxCallback(data);
                }
                else {
                    eval(fv_ajaxCallback + "(data);");
                }
            }
        });
    };
}

/*
divId : 페이징 태그가 그려질 div
pageIndx : 현재 페이지 위치가 저장될 input 태그 id
recordCount : 페이지당 레코드 수
totalCount : 전체 조회 건수
eventName : 페이징 하단의 숫자 등의 버튼이 클릭되었을 때 호출될 함수 이름
*/
var gfv_pageIndex = null;
var gfv_eventName = null;
function gfn_renderPaging(params){
    var divId = params.divId; //페이징이 그려질 div id
    gfv_pageIndex = params.pageIndex; //현재 위치가 저장될 input 태그
    var totalCount = params.totalCount; //전체 조회 건수
    var currentIndex = $("#"+params.pageIndex).val(); //현재 위치
    if($("#"+params.pageIndex).length == 0 || gfn_isNull(currentIndex) == true){
        currentIndex = 1;
    }
     
    var recordCount = params.recordCount; //페이지당 레코드 수
    if(gfn_isNull(recordCount) == true){
        recordCount = 20;
    }
    var totalIndexCount = Math.ceil(totalCount / recordCount); // 전체 인덱스 수
    gfv_eventName = params.eventName;
     
    $("#"+divId).empty();
    var preStr = "";
    var postStr = "";
    var str = "";
     
    var first = (parseInt((currentIndex-1) / 10) * 10) + 1;
    var last = (parseInt(totalIndexCount/10) == parseInt(currentIndex/10)) ? totalIndexCount%10 : 10;
    var prev = (parseInt((currentIndex-1)/10)*10) - 9 > 0 ? (parseInt((currentIndex-1)/10)*10) - 9 : 1;
    var next = (parseInt((currentIndex-1)/10)+1) * 10 + 1 < totalIndexCount ? (parseInt((currentIndex-1)/10)+1) * 10 + 1 : totalIndexCount;
     
    if(totalIndexCount > 10){ //전체 인덱스가 10이 넘을 경우, 맨앞, 앞 태그 작성
        preStr += "<a href='#this' class='pad_5' onclick='_movePage(1)'>[<<]</a>" +
                "<a href='#this' class='pad_5' onclick='_movePage("+prev+")'>[<]</a>";
    }
    else if(totalIndexCount <=10 && totalIndexCount > 1){ //전체 인덱스가 10보다 작을경우, 맨앞 태그 작성
        preStr += "<a href='#this' class='pad_5' onclick='_movePage(1)'>[<<]</a>";
    }
     
    if(totalIndexCount > 10){ //전체 인덱스가 10이 넘을 경우, 맨뒤, 뒤 태그 작성
        postStr += "<a href='#this' class='pad_5' onclick='_movePage("+next+")'>[>]</a>" +
                    "<a href='#this' class='pad_5' onclick='_movePage("+totalIndexCount+")'>[>>]</a>";
    }
    else if(totalIndexCount <=10 && totalIndexCount > 1){ //전체 인덱스가 10보다 작을경우, 맨뒤 태그 작성
        postStr += "<a href='#this' class='pad_5' onclick='_movePage("+totalIndexCount+")'>[>>]</a>";
    }
     
    for(var i=first; i<(first+last); i++){
        if(i != currentIndex){
            str += "<a href='#this' class='pad_5' onclick='_movePage("+i+")'>"+i+"</a>";
        }
        else{
            str += "<b><a href='#this' class='pad_5' onclick='_movePage("+i+")'>"+i+"</a></b>";
        }
    }
    $("#"+divId).append(preStr + str + postStr);
}
 
function _movePage(value){
    $("#"+gfv_pageIndex).val(value);
    if(typeof(gfv_eventName) == "function"){
        gfv_eventName(value);
    }
    else {
        eval(gfv_eventName + "(value);");
    }
}