function goPage(pno,psize){
            var itable = document.getElementById("idData");//获取table
            var num = itable.rows.length;//得到记录总数
            var totalPage = 0;
            var pageSize = psize;//一页显示pageSize条记录
            //计算总页数
            if((num/pageSize)!==(parseInt(num/pageSize))){
                totalPage=parseInt(num/pageSize)+1;
            }else{
                totalPage=parseInt(num/pageSize);
            }
            //当前页数
            var currentPage = pno;
            //获取当前页第一条、最后一条记录的行号
            var startRow = (currentPage - 1) * pageSize+1;
            var endRow = currentPage * pageSize;
            endRow = (endRow > num)? num : endRow;
            //修改table中当前页对应的行的属性为显示，非本页的记录为隐藏
            for(var i=1;i<(num+1);i++){
                var irow = itable.rows[i-1];
                if(i>=startRow && i<=endRow){
                    irow.style.display = "table-row";
                }else{
                    irow.style.display = "none";
                }
            }

            //分页页码列表
            var tempStr = "共"+num+"条记录   分"+totalPage+"页   当前第"+currentPage+"页   ";
            if(currentPage>1){
                tempStr += "<a onClick=\"goPage("+(1)+","+psize+")\">  首页   </a>";
                tempStr += "<a onClick=\"goPage("+(currentPage-1)+","+psize+")\"><   上一页   </a>"
            }else{
                tempStr += "   首页   ";
                tempStr += "<   上一页   ";
            }

            if(currentPage<totalPage){
                tempStr += "<a onClick=\"goPage("+(currentPage+1)+","+psize+")\">   下一页   ></a>";
                tempStr += "<a onClick=\"goPage("+(totalPage)+","+psize+")\">   尾页</a>";
            }else{
                tempStr += "   下一页   >";
                tempStr += "   尾页";
            }
            document.getElementById("changePages").innerHTML = tempStr;
}