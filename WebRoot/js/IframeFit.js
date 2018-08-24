function resetScroll(fm_id){
	if(!window.opera){
		var height=document.getElementById(fm_id).contentWindow.document.body.scrollHeight;
		if(document.getElementById(fm_id).document) {
			height=document.getElementById(fm_id).document.body.scrollHeight;
		}
		if(document.getElementById(fm_id).contentWindow.document) {
			 height=document.getElementById(fm_id).contentWindow.document.body.scrollHeight;
			 height-=1;
			 height = height + "px";
			 document.getElementById(fm_id).style.height=height;
		}
	}
} 
