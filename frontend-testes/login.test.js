const { test } = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

test('Login de administrador', async () => {

    const html = fs.readFileSync(
        path.join(__dirname,
            '../src/main/resources/static/login.html'),
        'utf8'
    );

    const script = html.match(/<script>([\s\S]*?)<\/script>/)[1];

    let enviarFormulario;

    const elementos = {
        username: { value: 'teste' },
        password: { value: 'senhaTeste' },
        mensagem: { textContent: '' },
        loginForm: {
            addEventListener: (evento, funcao) => {
                enviarFormulario = funcao;
            }
        }
    };

    const contexto = {
        document: {
            getElementById: id => elementos[id]
        },
        window: {
            location: { href: '' }
        },
        URLSearchParams,
        fetch: async () => ({
            ok: true,
            text: async () => 'ADMIN'
        })
    };

    vm.runInNewContext(script, contexto);

    await enviarFormulario({
        preventDefault: () => {}
    });

    assert.equal(
        contexto.window.location.href,
        'cadastrarFuncionario.html'
    );
});