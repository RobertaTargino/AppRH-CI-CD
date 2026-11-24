const { test } = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

test('Cadastro de funcionário', async () => {

    const html = fs.readFileSync(
        path.join(__dirname,
            '../src/main/resources/static/cadastrarFuncionario.html'),
        'utf8'
    );

    const script = html.match(/<script>([\s\S]*?)<\/script>/)[1];

    let mensagem = '';

    const contexto = {
        window: {
            location: { href: '' },
            addEventListener: () => {}
        },
        fetch: async () => ({
            ok: true
        }),
        alert: texto => {
            mensagem = texto;
        }
    };

    vm.runInNewContext(script, contexto);

    await contexto.onSubmit({
        preventDefault: () => {},
        target: {
            elements: {
                nome: { value: 'Funcionario Teste' },
                cargo: { value: 'Analista' },
                salario: { value: '2000' }
            }
        }
    });

    assert.equal(mensagem, 'Funcionário cadastrado');

    assert.equal(
        contexto.window.location.href,
        'listarFuncionarios.html'
    );
});