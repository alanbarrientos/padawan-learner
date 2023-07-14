export function getBackendUrlEndPoint(endPoint){
    let baseUrl;
    if (window.origin.endsWith("localhost:3000")) { // localhost develop with ng serving frontend
        baseUrl = 'http://localhost:8080/'+endPoint;
    } else if (window.origin.endsWith(":3000")) { // testing inside same network with ng serving frontend
        baseUrl = window.origin.replace(":3000", ":8080") + '/'+endPoint;
    } else { // testing in internet with public IP or domain
        baseUrl = window.origin +  '/'+endPoint;
    }
    return baseUrl
}
