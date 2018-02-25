package com.tenacity.free.util;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.util
 * @file_name: HttpClientUtils.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午9:43:31
 * @desc: http 请求工具
 */
@SuppressWarnings("deprecation")
public class HttpClientUtils {

	private HttpClientUtils() {
	}

	private static PoolingHttpClientConnectionManager connMgr;
	private static RequestConfig requestConfig;
	private static final int MAX_TIMEOUT = 7000;

	static {
		// 设置连接池
		connMgr = new PoolingHttpClientConnectionManager();
		// 设置连接池大小
		connMgr.setMaxTotal(100);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(MAX_TIMEOUT);
		// 设置读取超时
		configBuilder.setSocketTimeout(MAX_TIMEOUT);
		// 设置从连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
		requestConfig = configBuilder.build();

	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月14日 下午10:05:36
	 * @desc: 构建get请求的URL
	 * @param reqUrl
	 * @param params
	 * @return
	 */
	private static String buildUrl(String reqUrl, Map<String, String> params) {
		if (null == params) {
			return reqUrl;
		}
		StringBuilder query = new StringBuilder();
		Set<String> set = params.keySet();
		for (Object key : set) {
			query.append(String.format("%s=%s&", key, params.get(key)));
		}
		return reqUrl + "?" + query.toString();
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月14日 下午10:05:44
	 * @desc: 发送 GET 请求（HTTP），不带输入数据
	 * @param url
	 * @param charset
	 * @return
	 */
	public static String doGet(String url, Charset charset) {
		return doGet(url, charset, new HashMap<>());
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月14日 下午10:05:55
	 * @desc: 发送 GET 请求（HTTP），K-V形式
	 * @param url
	 * @param charset
	 * @param params
	 * @return
	 */
	public static String doGet(String url, Charset charset, Map<String, String> params) {

		url = buildUrl(url, params);
		String result = null;
		HttpClient httpClient = HttpClients.createSystem();
		try {
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = IOUtils.toString(instream, charset);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月14日 下午10:06:05
	 * @desc: 发送 POST 请求（HTTP），不带输入数据
	 * @param url
	 * @param charset
	 * @return
	 */
	public static String doPost(String url, String charset) {
		return doPost(url, charset, new HashMap<>());
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月14日 下午10:06:14
	 * @desc: 发送 POST 请求（HTTP），K-V形式
	 * @param url
	 * @param charset
	 * @param params
	 * @return
	 */
	public static String doPost(String url, String charset, Map<Object, Object> params) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String httpStr = null;
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			httpPost.setConfig(requestConfig);
			List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<Object, Object> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString());
				pairList.add(pair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(charset)));
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			httpStr = EntityUtils.toString(entity, charset);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月14日 下午10:02:46
	 * @desc: 发送 POST 请求（HTTP），JSON形式
	 * @param url
	 * @param charset
	 * @param json
	 * @return
	 */
	public static String doPost(String url, String charset, Object json) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String httpStr = null;
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;

		try {
			httpPost.setConfig(requestConfig);
			StringEntity stringEntity = new StringEntity(json.toString(), charset);// 解决中文乱码问题
			stringEntity.setContentEncoding(charset);
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			httpStr = EntityUtils.toString(entity, charset);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月14日 下午10:02:57
	 * @desc: http get请求共用方法
	 * @param reqUrl
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static String sendGet(String reqUrl, Map<String, String> params) throws Exception {
		InputStream inputStream = null;
		HttpGet request = new HttpGet();
		try {
			String url = buildUrl(reqUrl, params);
			HttpClient client = new DefaultHttpClient();
			request.setHeader("Accept-Encoding", "gzip");
			request.setURI(new URI(url));

			HttpResponse response = client.execute(request);

			inputStream = response.getEntity().getContent();
			String result = getJsonStringFromGZIP(inputStream);
			return result;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			request.releaseConnection();
		}

	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月14日 下午10:04:05
	 * @desc: http post请求共用方法
	 * @param reqUrl
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static String sendPost(String reqUrl, String params) throws Exception {
		try {
			if (params != null && !"".equals(params)) {
				try {
					HttpClient client = new DefaultHttpClient();
					HttpPost request = new HttpPost(reqUrl);

					StringEntity stringEntity = new StringEntity(params, ContentType.create("text/json", "UTF-8"));
					request.setEntity(stringEntity);
					HttpResponse response = client.execute(request);
					String resultStr = EntityUtils.toString(response.getEntity());
					return resultStr;
				} catch (Exception ex) {
					ex.printStackTrace();
					throw new Exception("网络连接失败,请连接网络后再试");
				}
			} else {
				throw new Exception("参数不全，请稍后重试");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("发送未知异常");
		}
	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月14日 下午10:04:17
	 * @desc: http post请求json数据
	 * @param urls
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String sendPostBuffer(String urls, String params) throws ClientProtocolException, IOException {
		HttpPost request = new HttpPost(urls);

		StringEntity se = new StringEntity(params, HTTP.UTF_8);
		request.setEntity(se);
		// 发送请求
		@SuppressWarnings("resource")
		HttpResponse httpResponse = new DefaultHttpClient().execute(request);
		// 得到应答的字符串，这也是一个 JSON 格式保存的数据
		String retSrc = EntityUtils.toString(httpResponse.getEntity());
		request.releaseConnection();
		return retSrc;

	}

	/**
	 * @author: free.zhang
	 * @datetime: 2018年1月14日 下午10:04:26
	 * @desc: http请求发送xml内容
	 * @param urlStr
	 * @param xmlInfo
	 * @return
	 */
	public static String sendXmlPost(String urlStr, String xmlInfo) {
		// xmlInfo xml具体字符串
		try {
			URL url = new URL(urlStr);
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("Pragma:", "no-cache");
			con.setRequestProperty("Cache-Control", "no-cache");
			con.setRequestProperty("Content-Type", "text/xml");
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
			out.write(new String(xmlInfo.getBytes("utf-8")));
			out.flush();
			out.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String lines = "";
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				lines = lines + line;
			}
			return lines; // 返回请求结果
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "fail";
	}

	private static String getJsonStringFromGZIP(InputStream is) {
		String jsonString = null;
		try {
			BufferedInputStream bis = new BufferedInputStream(is);
			bis.mark(2);
			// 取前两个字节
			byte[] header = new byte[2];
			int result = bis.read(header);
			// reset输入流到开始位置
			bis.reset();
			// 判断是否是GZIP格式
			int headerData = getShort(header);
			// Gzip 流 的前两个字节是 0x1f8b
			if (result != -1 && headerData == 0x1f8b) {
				is = new GZIPInputStream(bis);
			} else {
				is = bis;
			}
			InputStreamReader reader = new InputStreamReader(is, "utf-8");
			char[] data = new char[100];
			int readSize;
			StringBuffer sb = new StringBuffer();
			while ((readSize = reader.read(data)) > 0) {
				sb.append(data, 0, readSize);
			}
			jsonString = sb.toString();
			bis.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	private static int getShort(byte[] data) {
		return (data[0] << 8) | data[1] & 0xFF;
	}

	private static final String GET = "GET";
	private static final String POST = "POST";
	private static String CHARSET = "UTF-8";

	private static final SSLSocketFactory sslSocketFactory = initSSLSocketFactory();
	private static final TrustAnyHostnameVerifier trustAnyHostnameVerifier = new TrustAnyHostnameVerifier();

	/**
	 * @class_name TrustAnyHostnameVerifier
	 * @method
	 * @description https域名校验
	 * @author free.zhang
	 * @date 2018/1/10 10:43
	 * @param '
	 * @return
	 */
	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * @class_name TrustAnyTrustManager
	 * @method
	 * @description https证书管理
	 * @author free.zhang
	 * @date 2018/1/10 10:47
	 * @param '
	 * @return
	 */
	private static class TrustAnyTrustManager implements X509TrustManager {
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
	}

	private static SSLSocketFactory initSSLSocketFactory() {
		try {
			TrustManager[] tm = { new TrustAnyTrustManager() };
			// ("TLS", "SunJSSE");
			SSLContext sslContext = SSLContext.getInstance("TLS"); 
			sslContext.init(null, tm, new java.security.SecureRandom());
			return sslContext.getSocketFactory();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void setCharSet(String charSet) {
		if (StringUtils.isEmpty(charSet)) {
			throw new IllegalArgumentException("charSet can not be blank.");
		}
		CHARSET = charSet;
	}

	private static HttpURLConnection getHttpConnection(String url, String method, Map<String, String> headers)
			throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		URL _url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
		if (conn instanceof HttpsURLConnection) {
			((HttpsURLConnection) conn).setSSLSocketFactory(sslSocketFactory);
			((HttpsURLConnection) conn).setHostnameVerifier(trustAnyHostnameVerifier);
		}

		conn.setRequestMethod(method);
		conn.setDoOutput(true);
		conn.setDoInput(true);

		conn.setConnectTimeout(19000);
		conn.setReadTimeout(19000);

		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");

		if (headers != null && !headers.isEmpty()) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}

		return conn;
	}

	/**
	 * @class_name HttpClientUtils
	 * @method get
	 * @description 发送get请求
	 * @author free.zhang
	 * @date 2018/1/10 10:51
	 * @param '[url,
	 *            queryParas, headers]
	 * @return java.lang.String
	 */
	public static String get(String url, Map<String, String> queryParas, Map<String, String> headers) {

		HttpURLConnection conn = null;
		try {
			conn = getHttpConnection(buildUrlWithQueryString(url, queryParas), GET, headers);
			conn.connect();
			return readResponseString(conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public static String get(String url, Map<String, String> queryParas) {
		return get(url, queryParas, null);
	}

	public static String get(String url) {
		return get(url, null, null);
	}

	/**
	 * @class_name HttpClientUtils
	 * @method post
	 * @description 发送post请求
	 * @author free.zhang
	 * @date 2018/1/10 10:51
	 * @param '[url,
	 *            queryParas, data, headers]
	 * @return java.lang.String
	 */
	public static String post(String url, Map<String, String> queryParas, String data, Map<String, String> headers) {

		HttpURLConnection conn = null;
		try {
			conn = getHttpConnection(buildUrlWithQueryString(url, queryParas), POST, headers);
			conn.connect();

			if (data != null) {
				OutputStream out = conn.getOutputStream();
				out.write(data.getBytes(CHARSET));
				out.flush();
				out.close();
			}

			return readResponseString(conn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public static String post(String url, Map<String, String> queryParas, String data) {
		return post(url, queryParas, data, null);
	}

	public static String post(String url, String data, Map<String, String> headers) {
		return post(url, null, data, headers);
	}

	public static String post(String url, String data) {
		return post(url, null, data, null);
	}

	private static String readResponseString(HttpURLConnection conn) {
		BufferedReader reader = null;
		try {
			StringBuilder ret;
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), CHARSET));
			String line = reader.readLine();
			if (line != null) {
				ret = new StringBuilder();
				ret.append(line);
			} else {
				return "";
			}

			while ((line = reader.readLine()) != null) {
				ret.append('\n').append(line);
			}
			return ret.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.getMessage();
				}
			}
		}
	}

	/**
	 * @class_name HttpClientUtils
	 * @method buildUrlWithQueryString
	 * @description 拼接url参数
	 * @author free.zhang
	 * @date 2018/1/10 10:50
	 * @param '[url,
	 *            queryParas]
	 * @return java.lang.String
	 */
	private static String buildUrlWithQueryString(String url, Map<String, String> queryParas) {
		if (queryParas == null || queryParas.isEmpty()) {
			return url;
		}

		StringBuilder sb = new StringBuilder(url);
		boolean isFirst;
		if (url.indexOf('?') == -1) {
			isFirst = true;
			sb.append('?');
		} else {
			isFirst = false;
		}

		for (Map.Entry<String, String> entry : queryParas.entrySet()) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append('&');
			}
			String key = entry.getKey();
			String value = entry.getValue();
			if (StringUtils.isNotBlank(value)) {
				try {
					value = URLEncoder.encode(value, CHARSET);
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}
			sb.append(key).append('=').append(value);
		}
		return sb.toString();
	}

	public static String readData(HttpServletRequest request) {
		BufferedReader br = null;
		try {
			StringBuilder ret;
			br = request.getReader();

			String line = br.readLine();
			if (line != null) {
				ret = new StringBuilder();
				ret.append(line);
			} else {
				return "";
			}

			while ((line = br.readLine()) != null) {
				ret.append('\n').append(line);
			}

			return ret.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.getMessage();
				}
			}
		}
	}

	@Deprecated
	public static String readIncommingRequestData(HttpServletRequest request) {
		return readData(request);
	}
}
