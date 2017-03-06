using UnityEngine;
using System.Collections;

public class BestiaController : MonoBehaviour {

    public int hits;
    private Animator animator;
    public bool muerto;
    public bool izquierda;
    public float velocidad;

	// Use this for initialization
	void Start () {
        hits = 0;
        animator = this.gameObject.GetComponent<Animator>();
        muerto = false;

    }
	
	// Update is called once per frame
	void Update () {
        if (!muerto)
        {
            animator.Play("Walk");
            if (izquierda)
            {
                this.GetComponent<SpriteRenderer>().flipX = false;
                transform.position += new Vector3(velocidad * (-1), 0);
            }
            else
            {
                this.GetComponent<SpriteRenderer>().flipX = true;
                transform.position += new Vector3(velocidad, 0);
            }
        }
        if(hits > 60)
        {
            transform.localScale = new Vector3(1.0f, 1.0f, 1.0f);
            animator.Play("Die");
            muerto = true;
        }

	}

    void OnTriggerEnter(Collider other)
    {
       // Debug.Log(other.tag); Debug.Log(other.name);
        if (!muerto)
        {
            if(other.name == "MC")
            {
                other.GetComponent<Rigidbody>().AddForce(Vector3.up * 200);
                other.GetComponent<PlayerCont>().actualizarVidas(-1);
            }
        }
        

    }

    void OnTriggerExit(Collider other)
    {
        //Debug.Log(other.tag); Debug.Log(other.name);
        if (other.tag == "Suelo")
        {
            izquierda = !izquierda;
        }
    }

}
