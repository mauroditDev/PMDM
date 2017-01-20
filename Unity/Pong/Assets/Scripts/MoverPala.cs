using UnityEngine;
using System.Collections;

public class MoverPala : MonoBehaviour {

    public float velocidad;
    private float movEnY;
    private Vector3 movim;
    private Vector3 posInicial;
    public pulsarPantalla up;
    public pulsarPantalla down;
    // Use this for initialization
    void Start () {
        posInicial = this.transform.position;
	}
	
	// Update is called once per frame
	void Update () {
        if (up.pulsado)
        {
            movEnY = 1;
        }
        
        else
        {
            if (down.pulsado)
            {
                movEnY = -1;
            }
            else
            {
                movEnY = Input.GetAxis("Horizontal")*-1;
            }
        }
        
        movEnY = movEnY * velocidad;
        movim = new Vector3(0.0f, movEnY * Time.deltaTime);
        this.transform.position += movim;
        if(this.transform.position.y > 2.45f)
        {
            this.transform.position = new Vector3(this.transform.position.x, 2.45f);
        }
        if(this.transform.position.y < -2.45f)
        {
            this.transform.position = new Vector3(this.transform.position.x, -2.45f);
        }
    }

    public void Reset()
    {
        this.transform.position = posInicial;
    }

}
