/**
 * 
 */
function confirmar(id) {
	let resposta = confirm(" Confirmar a exclusão do produto ? ")
	if (resposta === true) {
		window.location.href = "delete?id=" + id
	}
}