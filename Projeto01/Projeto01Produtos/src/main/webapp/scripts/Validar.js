



function Validar() {
	let nome = frmcadastro.nome.value
	let codigo = frmcadastro.codigo.value
	let quantidade = frmcadastro.quantidade.value
	let valor = frmcadastro.valor.value
	
	 if (nome === "") {
		 alert("Preencha o campo nome")
		 frmcadastro.nome.focus()
		 return false
	 } else if (codigo ==="") {
		 alert("preencha o campo codigo")
		 frmcadastro.fone.focus
		 return false
	 } else if (quantidade ==="") {
		 alert("Preencha o campo quantidade")
		 frmcadastro.quantidade.focus
		 return false
	 } else if (valor ==="") {
		 alert(" Preencha o campo valor ")
		 frmcadastro.valor.focus
		 return false
	 }
	 
	  {
		 document.forms["frmcadastro"].submit()
	 }
}