-- Inserção em dados_pagamento
INSERT INTO dados_pagamento (id, numero_cartao, nome_titular, data_validade, cvv) VALUES
  ('11111111-1111-1111-1111-111111111111', '4111111111111111', 'Edu Sócrates', '12/27', '123'),
  ('22222222-2222-2222-2222-222222222222', '5500000000000004', 'Maria Silva', '09/26', '456');

-- Inserção em pedido
INSERT INTO pedido (id, cliente_id, status, dados_pagamento_id, valor_total) VALUES
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '33333333-3333-3333-3333-333333333333', 'ABERTO', '11111111-1111-1111-1111-111111111111', 149.90),
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '44444444-4444-4444-4444-444444444444', 'ABERTO', '22222222-2222-2222-2222-222222222222', 239.80);

-- Inserção em itens_pedido
INSERT INTO itens_pedido (sku_produto, quantidade, preco_unitario, pedido_id) VALUES
  ('SKU-123', 2, 49.95, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
  ('SKU-456', 1, 49.99, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
  ('SKU-789', 2, 119.90, 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb');
