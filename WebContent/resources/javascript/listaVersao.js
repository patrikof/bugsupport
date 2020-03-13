
/* Tabelas do Sistema ( dataTable ) 
		*/
            $(document).ready(
            		function()
                    {
                        // $('[id="j_idt20:lista_cliente"]').dataTable();
            	         // $('.table').dataTable({    	
            	
            	
            	
            	        $('#listaVersao').dataTable({
                                "oLanguage": {"sUrl": contextPath+"/resources/DataTable/idioma/br.txt"},
                                "sPaginationType": "full_numbers",                                                                               
                                "bServerSide": true,
                                "sAjaxSource": contextPath+"/VersaoGsonServlet",
                                "bProcessing": true,                                
                            	"aoColumns": [
											{"mDataProp":"produto.descricao", "sName":"Produto"},
                            	            {"mDataProp":"descricao", "sName":"Versão"},                            		 
                            		 		{"mDataProp":"ativa", "sName":"Ativa"},
                            	            {"mDataProp":"id", "bSortable": false},
                            		 		{"mDataProp":"id", "bSortable": false}
                            	            ],
                                
                                "columnDefs": [
                                               {
                                                   // The `data` parameter
													// refers to the data for
													// the cell (defined by the
                                                   // `data` option, which
													// defaults to the column
													// being worked with, in
                                                   // this case `data: 0`.
                                                   "render": function ( data, type, row ) {
                                                	   /*console.log(data);*/
                                                	   if (data)
                                                		   return "Sim"
                                                       else 
                                                    	   return "Não";
                                                 }
                                               ,"targets": [2]
                                               },
                                               {
                                                   // The `data` parameter
													// refers to the data for
													// the cell (defined by the
                                                   // `data` option, which
													// defaults to the column
													// being worked with, in
                                                   // this case `data: 0`.
                                                   "render": function ( data, type, row ) {
                                                	   /*console.log(data);*/
                                                       return '<a  class="glyphicon glyphicon-edit" href="form.jsf?id='+data+'" />';
                                                       
                                                 }
                                               ,"targets": [3]
                                               },
                                               {                                                
                                                   "render": function ( data, type, row ) {
                                                	  
                                                	  // return '<a id="rm_'+data+'" class="glyphicon glyphicon-remove" href="#" onclick="removerVersao('+data+')" ></a>';
                                                	   
                                                	   return '<a id="rm_'+data+'" class="glyphicon glyphicon-remove" href="#" onclick="if (!confirm(\'Deseja realmente excluir a Versão '+row.descricao+'?\')){ return false}else{removerVersao('+data+')}" ></a>'; 
                                                	                                            
                                                 }
                                               ,"targets": [4]
                                               }
                                           ]
                            	
                                });
                    });
                    
                    
            		
		