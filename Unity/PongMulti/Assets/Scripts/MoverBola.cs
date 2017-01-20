using UnityEngine;
using System.Collections;

public class MoverBola : MonoBehaviour {
    public float velocidadInicial;
    public Rigidbody rb;
    private bool enJuego;
    public Transform barra;
    private Vector3 posicionInicial;
    public pulsarPantalla start;
    public Vidas vidas;
    public AudioSource aud;
    private float rndmX;
    private float rndmY;



    void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.CompareTag("izq"))
        {
            Reset();
            vidas.Dch();
            aud.Play();
        }
        if (other.gameObject.CompareTag("dch"))
        {
            Reset();
            vidas.Izq();
            aud.Play();
        }
    }
    // Use this for initialization
    void Start () {
        rb = this.GetComponent<Rigidbody>();
      //  barra = this.gameObject.GetComponentInParent<Transform>();
        posicionInicial = this.transform.position;
        //this.transform.SetParent(barra);
    }
	
	// Update is called once per frame
	void Update () {

        if (!enJuego)
        {
            if(start.pulsado || Input.GetButton("Fire1"))
            {
                enJuego = true;
              //  this.transform.SetParent(null);
                rb.isKinematic = false;
                rndmX = 0;
                while(rndmX<1 && rndmX>-1){
                    rndmX = Random.Range(-3, 3);
                }
                rndmY = 0;
                while (rndmY < 1 && rndmY > -1)
                {
                    rndmY = Random.Range(-3, 3);
                }
                rb.AddForce(
                    new Vector3(
                        velocidadInicial * rndmX,
                        velocidadInicial * rndmY
                        )
                    );

            }
        }
	}

    public void Reset()
    {

        this.transform.position = posicionInicial;
        rb.isKinematic = true;
        this.transform.SetParent(barra);
        enJuego = false;

    }
}
