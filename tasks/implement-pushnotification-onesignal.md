# OneSignal プッシュ通知実装タスクリスト

## 進行方向
- tasks/.workflow.mdを参照

## 🔧 OneSignal セットアップ（優先度：高）

### 1. OneSignal アカウント作成と新しいアプリの追加
- [ ] OneSignal（https://onesignal.com/）にアカウント作成またはログイン
- [ ] 新しいアプリを作成
- [ ] プラットフォームとして「Android」を選択
- [ ] アプリ名とパッケージ名（`io.github.mikan.sample.pushnotification`）を入力

### 2. OneSignal App ID と REST API Key を取得
- [ ] OneSignal Dashboard の Settings > Keys & IDs から App ID をコピー
- [ ] REST API Key をコピー（後でAPI テストに使用）
- [ ] 認証情報を安全に保存（git に含めない）

### 3. build.gradle.kts に OneSignal SDK 依存関係を追加
- [ ] プロジェクトレベルの build.gradle.kts にOneSignal Gradle Plugin を追加
- [ ] アプリレベルの build.gradle.kts に OneSignal SDK 依存関係を追加
- [ ] 必要に応じて他の依存関係（Kotlin coroutines など）を追加

### 4. AndroidManifest.xml に必要な権限を追加
- [ ] INTERNET 権限を追加
- [ ] OneSignal が自動的に追加する権限を確認
- [ ] 必要に応じて追加の権限を設定

## 🚀 OneSignal 初期化（優先度：高）

### 5. Application クラスまたは MainActivity で OneSignal を初期化
- [ ] カスタム Application クラスを作成（推奨）またはMainActivity で初期化
- [ ] AndroidManifest.xml にApplication クラスを登録
- [ ] OneSignal の初期化処理を実装

### 6. OneSignal の setAppId と初期化設定を実装
- [ ] `OneSignal.setAppId()` でApp ID を設定
- [ ] 開発環境用とプロダクション環境用の設定分離を検討
- [ ] 初期化時のログレベル設定

## 🔔 通知権限とハンドリング（優先度：中）

### 7. 通知権限リクエストの実装
- [ ] Android 13以上での通知権限リクエストを実装
- [ ] `OneSignal.promptForPushNotifications()` を使用
- [ ] 適切なタイミング（アプリ起動時、機能説明後など）で権限をリクエスト

### 8. ユーザーの通知許可状態の確認と処理
- [ ] `OneSignal.getDeviceState()` で通知許可状態を確認
- [ ] 権限が拒否された場合の代替処理を実装
- [ ] 設定画面への誘導機能を追加

### 9. 通知受信時のカスタムハンドラーを実装
- [ ] `OneSignal.setNotificationWillShowInForegroundHandler` を実装
- [ ] フォアグラウンド時の通知表示をカスタマイズ
- [ ] 通知データの処理とローカル保存

### 10. 通知クリック時の処理を実装
- [ ] `OneSignal.setNotificationOpenedHandler` を実装
- [ ] 通知クリック時のアプリ内ナビゲーション処理
- [ ] ディープリンクの処理

## 👤 ユーザー管理（優先度：中）

### 11. ユーザー（Player）ID の取得と管理
- [ ] `OneSignal.getDeviceState()?.userId` でPlayer ID を取得
- [ ] Player ID の変更監視を実装
- [ ] ローカルストレージへのPlayer ID 保存

### 12. カスタムユーザータグの設定
- [ ] `OneSignal.sendTag()` でユーザー属性を設定
- [ ] ユーザーの言語、地域、設定などをタグとして追加
- [ ] タグに基づくセグメント配信の準備

## 🎨 UI 実装（優先度：中〜低）

### 13. Player ID を表示する UI を追加
- [ ] MainActivity にPlayer ID 表示エリアを追加
- [ ] Player ID をクリップボードにコピーする機能
- [ ] 通知許可状態の表示

### 14. 通知送信テスト用の UI を追加
- [ ] 管理者用の通知送信テスト画面を作成（任意）
- [ ] 自分宛てにテスト通知を送信する機能
- [ ] 各種通知タイプのテスト機能

## 🧪 OneSignal ローカルテストと検証（優先度：中）

### 15. OneSignal Dashboard から手動でテスト通知を送信
- [ ] Dashboard > Messages > New Push から新しい通知を作成
- [ ] 「Send to Particular Users」で特定のPlayer ID にテスト送信
- [ ] 異なるメッセージタイプ（テキスト、画像、アクションボタン）でテスト

### 16. 特定の Player ID への通知送信テスト
- [ ] 取得したPlayer ID を使用してターゲット送信
- [ ] 複数デバイス/エミュレーターでのテスト
- [ ] タグに基づくセグメント送信のテスト

### 17. OneSignal REST API を使用した通知送信テスト
- [ ] OneSignal REST API（v1）を使用した通知送信を実装
- [ ] 認証ヘッダー（Basic Auth with REST API Key）を設定
- [ ] JSON ペイロードの作成と送信

### 18. Postman/curl を使用した API 直接テスト
- [ ] PostmanコレクションまたはcURLコマンドでAPI テスト
- [ ] 様々なペイロード（Basic、Rich、Action Buttons）でテスト
- [ ] スケジュール配信のテスト

## 📱 動作シナリオテスト（優先度：中）

### 19. フォアグラウンド時の通知動作テスト
- [ ] アプリがアクティブな状態での通知受信テスト
- [ ] カスタムハンドラーの動作確認
- [ ] UI への通知表示テスト

### 20. バックグラウンド時の通知動作テスト
- [ ] アプリがバックグラウンドの状態での通知受信テスト
- [ ] 通知トレイへの表示確認
- [ ] 通知クリック時のアプリ復帰動作確認

### 21. アプリ終了時の通知動作テスト
- [ ] アプリが完全に終了している状態での通知受信テスト
- [ ] 通知クリック時のアプリ起動動作確認
- [ ] バックグラウンドプロセスの動作確認

## 🔥 高度な機能テスト（優先度：低）

### 22. セグメント化された通知の送信テスト
- [ ] ユーザータグに基づくセグメント作成
- [ ] 特定セグメントへの通知送信テスト
- [ ] A/Bテスト機能の検証

### 23. スケジュール通知の設定とテスト
- [ ] 遅延送信（Delayed Send）の設定
- [ ] 特定時間での送信スケジュール設定
- [ ] タイムゾーン対応の確認

## ✅ 最終検証（優先度：高）

### 24. アプリのビルドとエラーチェック
- [ ] プロジェクトの完全なビルドを実行
- [ ] Lint チェックを実行
- [ ] ProGuard/R8 の設定確認（リリースビルド用）

### 25. OneSignal 統計とアナリティクスの確認
- [ ] OneSignal Dashboard の Delivery 統計を確認
- [ ] Click Through Rate (CTR) の確認
- [ ] User Insights の活用

### 26. プッシュ通知の動作確認とデバッグ
- [ ] すべてのシナリオでの通知動作を確認
- [ ] OneSignal ログの確認とデバッグ
- [ ] エラーハンドリングの最終確認

## 📝 補足情報

### 重要なファイル
- `build.gradle.kts` - OneSignal SDK の依存関係
- `AndroidManifest.xml` - 権限とApplication クラスの設定
- カスタムApplication クラス - OneSignal の初期化
- `MainActivity.kt` - Player ID 表示とUI 実装

### OneSignal 設定値
```kotlin
// 例：App ID の設定
OneSignal.setAppId("your-onesignal-app-id")

// REST API Key（サーバーサイドで使用）
"Basic your-rest-api-key"
```

### 参考リンク
- [OneSignal Android SDK Setup](https://documentation.onesignal.com/docs/android-sdk-setup)
- [OneSignal REST API Reference](https://documentation.onesignal.com/reference/create-notification)
- [Android通知ベストプラクティス](https://developer.android.com/guide/topics/ui/notifiers/notifications)

### トラブルシューティング
- OneSignal App ID が正しく設定されていることを確認
- パッケージ名がOneSignal アプリ設定と一致することを確認
- Android 13以上で通知権限が正しく取得されていることを確認
- プロビジョニングプロファイルと署名設定の確認（リリース時）
- ネットワーク接続とファイアウォール設定の確認

### セキュリティ注意事項
- REST API Key は決してクライアントサイドに含めない
- App ID のみクライアントサイドで使用
- ユーザーデータのプライバシー設定を適切に行う