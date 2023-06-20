
function gerarLogin() {
    const nome = document.getElementById('nome').value.toLowerCase();
    const sobrenome = document.getElementById('sobrenome').value.toLowerCase();
    const login = nome + '.' + sobrenome;
    document.getElementById('login').value = login;
}

function limparEndereco() {
    document.getElementById('logradouro').value = '';
    document.getElementById('numero').value = '';
    document.getElementById('complemento').value = '';
    document.getElementById('cidade').value = '';
    document.getElementById('estado').value = '';
}

function preencherEndereco(cep) {
    const xhr = new XMLHttpRequest();
    const url = `https://viacep.com.br/ws/${cep}/json/`;

    xhr.open('GET', url, true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            const endereco = JSON.parse(xhr.responseText);
            if (!endereco.erro) {
                document.getElementById('logradouro').value = endereco.logradouro;
                document.getElementById('numero').value = endereco.numero;
                document.getElementById('complemento').value = endereco.complemento;
                document.getElementById('cidade').value = endereco.localidade;
                document.getElementById('estado').value = endereco.uf;
                document.getElementById('cep-erro').style.display = 'none';
            } else {
                limparEndereco();
                document.getElementById('cep-erro').style.display = 'block';
            }
        } else {
            limparEndereco();
            document.getElementById('cep-erro').style.display = 'block';
        }
    };

    xhr.onerror = function () {
        limparEndereco();
        document.getElementById('cep-erro').style.display = 'block';
    };

    xhr.send();
}
