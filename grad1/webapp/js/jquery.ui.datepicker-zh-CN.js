jQuery(function($){  
    $.datepicker.regional['zh-CN'] = {  
        closeText: '确认',  
        prevText: '<上月',  
        nextText: '下月>',  
        currentText: '今天',  
        monthNames: ['01','02','03','04','05','06',  
        '07','08','09','10','11','12'],  
        monthNamesShort: ['01','02','03','04','05','06',  
                          '07','08','09','10','11','12'],  
        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
        dayNamesMin: ['日','一','二','三','四','五','六'],  
        weekHeader: '周',  
        dateFormat: 'yyyy-MM-dd',  
        firstDay: 1,  
        isRTL: false,  
        showMonthAfterYear: true,  
        yearSuffix: '年'};  
    $.datepicker.setDefaults($.datepicker.regional['zh-CN']);  
});