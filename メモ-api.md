# api
`UnifiedBuilder`で登録する
実際には`UnifiedBuilder.create(String)`でIDを使うItem,BLock,BlockItemを作成する準備をする
`UnifiedBuilder.create(String).build()`が登録の最小構成(実際にはItem、Blockを指定してないから何も登録されない)
`UnifiedBuilder#item(ItemBuilder.create())`ItemBuilderを引数に設定することでItemを作るように指示する。`.create()`の中は何も指定しない
`UnifiedBuilder#block(BlockBuilder.create())`BlockBuilderを引数に設定することでBlockを作るように指示する。`.create()`の中は何も指定しない
`BlockBuilder#blockEntitySupplier(BlockEntityType.BlockEntitySupplier<? extends BaseBlockEntity>)`BlockEntityにするためのBlockEntityTypeを指定する。このメゾッドを付けなかった場合BlockEntityにならない。

# フロー
UnifiedBuilder.create("ID").block(BlockBuilder.create().blockEntitySupplier()).item(ItemBuilder.create()).build();
UnifiedRegistryImpl.register(IEventBus);

UnifiedBuilder.create("ID")
UnifiedBuilderのインスタンス化(newされる)
登録するItem、BlockのIDを決める
↓
UnifiedBuilder.create("ID").block()
Blockのデータを作成する為の容器？
↓
BlockBuilder.create()
標準のBlockのデータを作成する
(BlockBuilderのインスタンス化)
↓
BlockBuilder.create().blockEntitySupplier()
ブロックエンティティのタイプを指定
↓
UnifiedBuilder.create("ID").item()
IDでItemを作成するためのデータを入れるための容器？
↓
ItemBuilder.create()
標準のItemデータを作成する
(ItemBuilderのインスタンス化)
↓
UnifiedBuilder.create("ID").build()
UnifiedEntryを作成してUnifiedRegistry.ENTRIESに保存
↓
UnifiedRegistryImpl.register()
UnifiedRegistry.ENTRIESからデータを取り出してMinecraftに登録する

# 問題点
同じIDを指定したときの動作が不明瞭(多分後から追加した物に上書きされる)
    おなじIDで追加できないようにすべきかな