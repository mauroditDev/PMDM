using UnityEngine;
using System.Collections;

public class caida : MonoBehaviour {

    public MoverBola bola;
    public MoverPala pala;
    public Vidas vidas;
    public AudioSource aud;

    void Start()
    {
    }

    void OnTriggerEnter(Collider other)
    {
        //if (other.gameObject.CompareTag("bola"))
        //{
            bola.Reset();
            pala.Reset();
           // vidas.pierdeVida();
            aud.Play();
        //}
    }
    
}
