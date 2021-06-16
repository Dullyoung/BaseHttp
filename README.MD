### 简介

基于kotlin,使用okhttp3+协程+gson解析封装Android网络请求框架

### 如何使用？

- 新建业务请求类继承BaseEngine 如：

  ``` kotlin
  class LoginEngine<T> : BaseEngine<T>(){
       suspend fun login(name: String, pwd: String): ResultInfo<T> {
          val deferred = CoroutineScope(GlobalScope.coroutineContext).async(Dispatchers.IO) {
              val params = HashMap<String, String>()
              params["name"] = name
              params["pwd"] = pwd
              post(
                  "接口地址",
                  object : TypeToken<ResultInfo<data里的数据类型>>() {}.type, params
              )
          }
          return deferred.await()
      }
  }
  ```

- 外部使用：

  ```kotlin
    GlobalScope.launch(Dispatchers.Main) {
              val loginEngine = LoginEngine<UserInfo>()
              loginEngine.login("666", "12345678")
          }
  ```



### 注意事项

- 数据实体类 用data class 配合gson ,用fastjson要用javaBean  或者加上kotlin reflect
- 实体类为空返回{} 不要返回""