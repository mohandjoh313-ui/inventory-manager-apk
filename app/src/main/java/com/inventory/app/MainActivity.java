Run echo "===== MANIFEST CONTENT ====="
===== MANIFEST CONTENT =====
$
package com.inventory.app;$
$
import android.app.Activity;$
import android.content.Intent;$
import android.net.Uri;$
import android.os.Bundle;$
import android.webkit.WebChromeClient;$
import android.webkit.WebSettings;$
import android.webkit.WebView;$
import android.webkit.WebViewClient;$
import android.webkit.ValueCallback;$
import androidx.webkit.WebViewAssetLoader;$
import androidx.webkit.WebViewClientCompat;$
$
public class MainActivity extends Activity {$
$
    private WebView webView;$
    private ValueCallback<Uri[]> fileCallback;$
    private static final int FILE_REQUEST = 1001;$
$
    @Override$
    protected void onCreate(Bundle savedInstanceState) {$
        super.onCreate(savedInstanceState);$
$
        webView = new WebView(this);$
        setContentView(webView);$
$
        WebSettings settings = webView.getSettings();$
        settings.setJavaScriptEnabled(true);$
        settings.setDomStorageEnabled(true);$
$
        WebViewAssetLoader assetLoader =$
            new WebViewAssetLoader.Builder()$
                .addPathHandler($
                    "/assets/",$
                    new WebViewAssetLoader.AssetsPathHandler(this)$
                )$
                .build();$
$
        webView.setWebViewClient(new WebViewClientCompat() {$
            @Override$
            public android.webkit.WebResourceResponse shouldInterceptRequest($
                    WebView view,$
                    android.webkit.WebResourceRequest request) {$
                return assetLoader.shouldInterceptRequest(request.getUrl());$
            }$
        });$
$
        webView.setWebChromeClient(new WebChromeClient() {$
            @Override$
            public boolean onShowFileChooser($
                    WebView view,$
                    ValueCallback<Uri[]> callback,$
                    FileChooserParams params) {$
$
                if (fileCallback != null) {$
                    fileCallback.onReceiveValue(null);$
                }$
$
                fileCallback = callback;$
$
                try {$
                    Intent intent = params.createIntent();$
                    startActivityForResult(intent, FILE_REQUEST);$
                    return true;$
                } catch (Exception e) {$
                    fileCallback = null;$
                    return false;$
                }$
            }$
        });$
$
        webView.loadUrl($
            "https://appassets.androidplatform.net/assets/index.html"$
        );$
    }$
$
    @Override$
    protected void onActivityResult($
            int requestCode, int resultCode, Intent data) {$
        super.onActivityResult(requestCode, resultCode, data);$
$
        if (requestCode == FILE_REQUEST && fileCallback != null) {$
            Uri[] results = null;$
$
            if (resultCode == RESULT_OK && data != null) {$
                Uri uri = data.getData();$
                if (uri != null) {$
                    results = new Uri[]{uri};$
                }$
            }$
$
            fileCallback.onReceiveValue(results);$
            fileCallback = null;$
        }$
    }$
$
    @Override$
    public void onBackPressed() {$
        if (webView != null && webView.canGoBack()) {$
            webView.goBack();$
        } else {$
            super.onBackPressed();$
        }$
    }$
$
    @Override$
    protected void onDestroy() {$
        if (fileCallback != null) {$
            fileCallback.onReceiveValue(null);$
            fileCallback = null;$
        }$
$
        if (webView != null) {$
            webView.destroy();$
            webView = null;$
        }$
$
        super.onDestroy();$
    }$
}$
===== FILE INFORMATION =====
app/src/main/AndroidManifest.xml: Java source, ASCII text
===== XML VALIDATION =====
XML ERROR: ParseError('syntax error: line 2, column 0')
Traceback (most recent call last):
  File "<stdin>", line 6, in <module>
  File "/usr/lib/python3.12/xml/etree/ElementTree.py", line 1204, in parse
    tree.parse(source, parser)
  File "/usr/lib/python3.12/xml/etree/ElementTree.py", line 569, in parse
    self._root = parser._parse_whole(source)
                 ^^^^^^^^^^^^^^^^^^^^^^^^^^^
xml.etree.ElementTree.ParseError: syntax error: line 2, column 0
Error: Process completed with exit code 1.
