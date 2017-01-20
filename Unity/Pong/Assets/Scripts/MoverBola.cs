using UnityEngine;
using System.Collections;

public class MoverBola : MonoBehaviour {
    public float velocidadInicial;
    public Rigidbody rb;
    private bool enJuego;
    public Transform barra;
    private Vector3 posicionInicial;
    public pulsarPantalla start;

	// Use this for initialization
	void Start () {
        rb = this.GetComponent<Rigidbody>();
      //  barra = this.gameObject.GetComponentInParent<Transform>();
        posicionInicial = this.transform.position;
        this.transform.SetParent(barra);
    }
	
	// Update is called once per frame
	void Update () {

        if (!enJuego)
        {
            if(start.pulsado || Input.GetButton("Fire1"))
            {
                enJuego = true;
                this.transform.SetParent(null);
                rb.isKinematic = false;
                rb.AddForce(new Vector3(velocidadInicial * (-1), velocidadInicial));

            }
        }
	}

    public void Reset()
    {

        this.transform.position = new Vector3(0, barra.position.y);
        rb.isKinematic = true;
        this.transform.SetParent(barra);
        enJuego = false;

    }
}
