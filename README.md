# spring-batch-example-app

## Chunkモデル
* タスクはItemReader、ItemProcessor、ItemWriterの3要素をそれぞれ実装する
* 一括コミットはできず、各要素ごとに中間コミットが実行される

## Taskletモデル
* Taskletに集約される
* 処理は自由にかける
* トランザクションはTasklet単位で行われる
    * 中間コミットしたい場合は自前で実装
* 処理途中でエラーが発生すると未処理データのみロールバックされる

## どちらを使えばよいか
* 併用することが基本
* よくあるのがベースをTaskletで書きつつ、Reader/Processor/Writerに分割すべきところは分割する方法

## 参考
https://terasoluna-batch.github.io/guideline/5.0.0.RELEASE/ja/Ch03_ChunkOrTasklet.html