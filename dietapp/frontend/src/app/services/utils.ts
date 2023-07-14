export function getBackendUrlEndPoint(endPoint:String){
  let baseUrl;
  if (window.origin.endsWith("localhost:4200")) { // localhost develop with ng serving frontend
    baseUrl = 'http://localhost:8080/'+endPoint+'/';
  } else if (window.origin.endsWith(":4200")) { // testing inside same network with ng serving frontend
    baseUrl = window.origin.replace(":4200", ":8080") + '/'+endPoint+'/';
  } else { // testing in internet with public IP or domain
    baseUrl = window.origin +  '/'+endPoint+'/';
  }
  return baseUrl
  // return baseUrl = 'http://192.168.20.184:8080/'+endPoint+'/'
}
