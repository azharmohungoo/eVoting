angular.module('app.services', [])

.factory('BlankFactory', [function(){

}])

.service('BlankService', [function(){

}])

.service('LoginService', function($q) {
  return {
    loginUser: function(name, pw) {
      var deferred = $q.defer();
      var promise = deferred.promise;

      if (name == 'codeX' && pw == 'password') {
        deferred.resolve('Welcome ' + name + '!');
      } else {
        deferred.reject('Wrong credentials.');
      }
      promise.success = function(fn) {
        promise.then(fn);
        return promise;
      }
      promise.error = function(fn) {
        promise.then(null, fn);
        return promise;
      }
      return promise;
    }
  }
})

  .factory("userService", ['$soap', '$http',function($soap, $http){
    var base_url = "http://127.0.0.1:8080/ws/evoting.wsdl";
    var sr ="<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:gs='http://spring.io/guides/gs-producing-web-service'>" +
      "<soapenv:Header/>" +
    "<soapenv:Body>" +
   "<gs:loginRequest>" +
    "<gs:username>test</gs:username>" +
    "<gs:password>test</gs:password>" +
    "</gs:loginRequest>" +
    "</soapenv:Body>"+
    "</soapenv:Envelope>";
    /*var sr =
      '<?xml version="1.0" encoding="utf-8"?>' +
      '<soapenv:Envelope ' +
      '<soapenv:Body>' +
      '<api:some_api_call soapenv:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">' +
      '<username xsi:type="xsd:string">login_username</username>' +
      '<password xsi:type="xsd:string">password</password>' +
      '</api:some_api_call>' +
      '</soapenv:Body>' +
      '</soapenv:Envelope>';*/
    return {
      GetUsers: function(){
       // $soap.post(url,action,params);
        //{ name: “Andrew” }
        var params = {username: "test", password:"test"};

       // return $soap.get(base_url,"login",params);
        //return $soap.post(base_url,"login",params);
        return $http({
          url:base_url,
          method:"GET",
          data: sr,
          type: "text/xml"
        });
      }
    }
  }])


;
