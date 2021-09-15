$(function(){
	//点击全选
	$(".checkall").on("click",function(){
		var flag = $(this)[0].checked;
		$(".checksin").each(function(){
			$(this)[0].checked = flag;
		});
		//计算总价
		result();
		// console.log(flag);
	});
	
	//单选
	$(".checksin").on("click",function(){
		var i=0;
		
		$(".checksin").each(function(){
			if($(this)[0].checked==false){
				$(".checkall")[0].checked = false;
			}else{
				i += 1;
				if(i == $(".checksin").length){
					$(".checkall")[0].checked = true;
				}
			}
		});
		//计算总价
		result();
	});
	
	//加号
	$(".plus").on("click",function(){
		var cartItemId = $(this).attr("cid");
		
		var inputNum = $("input.numchange[cid="+cartItemId+"]");
		
		inputNum.val(parseInt(inputNum.val())+1);
		 //单价
		 var price = $("span.price[cid="+cartItemId+"]").text();
		 //数量
		 var count =  inputNum.val();
		 //商品价格小计
		 var itemprice = parseFloat(price) * parseInt(count);
		 //给小计赋值
		 $("span.itemprice[cid="+cartItemId+"]").text(itemprice);
		 //计算总价
		 result();
		 
		 $.ajax({
				type:'get',
				url:'syncCartItemCount',  //请求的地址
				data:'cartItemId='+cartItemId+"&count="+count,
				success:function(data){
//					alert(data);
				}
			});
		 
	});
	
	//减号
	$(".sub").on("click",function(){
		var cartItemId = $(this).attr("cid");
		
		var inputNum = $("input.numchange[cid="+cartItemId+"]");
		
		inputNum.val(parseInt(inputNum.val()) - 1);
		if(parseInt(inputNum.val()) <= 1){
			inputNum.val(1);
		}
		
		 //单价
		 var price = $("span.price[cid="+cartItemId+"]").text();
		 //数量
		 var count =  inputNum.val();
		//商品价格小计
		var itemprice = parseFloat(price) * parseInt(count);
		//给小计赋值
		$("span.itemprice[cid="+cartItemId+"]").text(itemprice);
		//计算总价
		result();
		
		 $.ajax({
				type:'get',
				url:'syncCartItemCount',  //请求的地址
				data:'cartItemId='+cartItemId+"&count="+count,
				success:function(data){
//					alert(data);
				}
			});
	});
});


//计算总价
function result(){
	var allprice = 0 ; //总价
	var allnum = 0; //总数量
	$("li").each(function(){
		if($(this).find(".checksin")[0].checked == true){
			allprice += parseFloat($(this).find(".price").html()) * parseInt($(this).find(".numchange").val());
			allnum += parseInt($(this).find(".numchange").val());
		}else{
			allprice += 0;
			allnum += 0;
		}
	});
	
	$(".allprice").html(allprice);
	$(".allnum").html(allnum);
}

//删除
function del(){
	$("li").each(function(){
		if($(this).find(".checksin")[0].checked== true){
			$(this).remove();
		}
	});
}

function subOrder(){
//	var subCid;
//	$("li").each(function(){
//		if($(this).find(".checksin")[0].checked == true){
//			subCid += $(this).find(".checksin")[0].value;
//		}
//	});
	 var cidList=new Array();
	$("input.checksin:checked").each(function(){
		cidList.push($(this).val());
	});
	alert(cidList.join(','));
}