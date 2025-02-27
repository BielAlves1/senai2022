const express = require('express');
const router = express.Router();

const Item = require("../controllers/ItemSolController");

router.get("/solicitacoes/itens", Item.listarItensSol);
router.get("/solicitacoes/item/:Nome_produto", Item.listarProdDepto);
router.post("/solicitacoes/item", Item.criarItemSol);

module.exports = router;