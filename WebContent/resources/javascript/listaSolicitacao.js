$(document)
		.ready(
				function() {

					$('#listaSolicitacao')
							.dataTable(
									{
										"oLanguage" : {
											"sUrl" : contextPath
													+ "/resources/DataTable/idioma/br.txt"
										},
										"sPaginationType" : "full_numbers",
										"bServerSide" : true,
										"sAjaxSource" : contextPath
												+ "/SolicitacaoGsonServlet",
										"bProcessing" : true,
										"bFilter" : false,
										"aoColumns" : [
												{
													"mDataProp" : "id",
													"sName" : "Código"
												},
												{
													"mDataProp" : "cliente.nomeFantasia",
													"sName" : "Cliente"
												},
												{
													"mDataProp" : "tipoSolicitacao.descricao",
													"sName" : "Tipo"
												},
												{
													"mDataProp" : "produto.descricao",
													"sName" : "Produto"
												},
												{
													"mDataProp" : "assunto",
													"sName" : "Assunto"
												},
												{
													"mDataProp" : "responsavel.login",
													"sName" : "Responsável"
												},
												{
													"mDataProp" : "pessoaContato",
													"sName" : "Contato"
												},
												{
													"mDataProp" : "dataCadastro",
													"sName" : "Cadastro"
												}, {
													"mDataProp" : "id",
													"bSortable" : false
												}, {
													"mDataProp" : "id",
													"bSortable" : false
												}, {
													"mDataProp" : "id",
													"bSortable" : false
												}, {
													"mDataProp" : "id",
													"bSortable" : false
												} ],
										"columnDefs" : [
												{

													"render" : function(data,
															type, row) {
														if (data == null) {
															return "";
														} else {
															return data;
														}

													}

													,
													"targets" : [ 5,7 ]
												},
												{

													"render" : function(data,
															type, row) {
														/* console.log(data); */
														return '<a  class="glyphicon glyphicon-list-alt no-print" href = "'
																+ contextPath
																+ '/views/solicitacao/detalhes.jsf?id_dt='
																+ data + '" />';

													},
													"targets" : [ 8 ]
												},
												{
													"render" : function(data,
															type, row) {

														return '<a  class="glyphicon glyphicon-user no-print" href = "'
																+ contextPath
																+ '/SolicitacaoGsonServlet?id_resp='
																+ data + '" />';
														// return '<a
														// class="glyphicon
														// glyphicon-user
														// no-print"
														// href="/BugSupport/admin/alteraResponsavel.jsf?id_resp='+data+'"
														// />';
														// return '<a
														// class="glyphicon
														// glyphicon-user
														// no-print"
														// href="/BugSupport/admin/alteraResponsavel.jsf"
														// onclick="alterarResponsavel('+data+')"
														// />';

													},
													"targets" : [ 9 ]
												},
												{
													"render" : function(data,
															type, row) {
														/* console.log(data); */
														// return '<a
														// class="glyphicon
														// glyphicon-edit
														// no-print"
														// href="/BugSupport/views/solicitacao/form.jsf?id_edt='+data+'"
														// />';
														return '<a  class="glyphicon glyphicon-edit no-print" href = "'
																+ contextPath
																+ '/SolicitacaoGsonServlet?id_edt='
																+ data + '" />';

													},
													"targets" : [ 10 ]
												},
												{
													"render" : function(data,
															type, row) {

														// return '<a
														// id="rm_'+data+'"
														// class="glyphicon
														// glyphicon-remove
														// no-print" href="#"
														// onclick="removerSolicitacao('+data+')"
														// ></a>';
														return '<a id="rm_'
																+ data
																+ '" class="glyphicon glyphicon-remove" href="#" onclick="if (!confirm(\'Deseja realmente excluir a Solicitação '
																+ data
																+ '?\')){ return false}else{removerSolicitacao('
																+ data
																+ ')}" ></a>';

													},
													"targets" : [ 11 ]
												} ]

									});
				});
