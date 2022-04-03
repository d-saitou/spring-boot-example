# Spring Bootサンプルアプリケーション
\* [英語版](/README.md)

## 1. 概要
本プロジェクトは、Spring Bootを利用したWebアプリケーションサンプルのGradleプロジェクトです。

## 2. 実装
実装例として以下の機能を実装しています。

* 入力フォームバリデーション
* ファイルアップロード・ダウンロード
* DB CRUD
* 認証・認可
* AOP (トランザクション設定、ロギング)
* REST API
* メール送信 (非同期使用)
* 静的リソース参照 (Webjars利用)
* 例外処理
* 国際化
* タスクスケジューラー

## 3. 開発環境
本アプリケーションを実行する場合、以下の手順を実施してください。

1. 以下をインストールしてください。
	* JDK 11 以降
	* VS Code や Eclipse 等の IDE (Lombok・Gradleプラグインが必要)
	* MySQL 8.0

2. IDE上で本ソースプロジェクトをチェックアウトしてください。

3. 必要に応じて、設定ファイル [application.yml](/src/main/resources/application.yml) の以下パラメータを変更してください。  

| パラメータ                   | 説明                                   |
|:-----------------------------|:---------------------------------------|
| anchor-params.data-directory | ファイル格納場所(ログ・一時ファイル他) |
| anchor-params.mail-address   | メールアドレス                         |
| spring.mail.\*               | メール送信設定                         |
| spring.datasource.url        | データベース接続URL                    |
| spring.datasource.username   | データベースユーザ名                   |
| spring.datasource.password   | データベースパスワード                 |

4. [application.yml](/src/main/resources/application.yml) のパラメータに従って、MySQLにデータベースとユーザーを作成してください。

5. [db](/data/db) ディレクトリ配下のSQLファイルを実行してMySQLにテーブルを作成してください。  

6. SpringBootアプリケーションとしてこのアプリケーションを実行してください。
