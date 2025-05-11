CREATE TABLE dados_pagamento (
    id UUID PRIMARY KEY,
    numero_cartao VARCHAR(255) NOT NULL,
    nome_titular VARCHAR(255) NOT NULL,
    data_validade VARCHAR(10) NOT NULL,
    cvv VARCHAR(10) NOT NULL
);

CREATE TABLE pedido (
    id UUID PRIMARY KEY,
    cliente_id UUID NOT NULL,
    status VARCHAR(50) NOT NULL,
    dados_pagamento_id UUID,
    valor_total NUMERIC(19,2) NOT NULL,
    CONSTRAINT fk_pedido_dados_pagamento FOREIGN KEY (dados_pagamento_id) REFERENCES dados_pagamento(id)
);

CREATE TABLE itens_pedido (
    id SERIAL PRIMARY KEY,
    sku_produto VARCHAR(255) NOT NULL,
    quantidade INTEGER NOT NULL,
    preco_unitario NUMERIC(19,2) NOT NULL,
    pedido_id UUID NOT NULL,
    CONSTRAINT fk_item_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id) ON DELETE CASCADE
);
