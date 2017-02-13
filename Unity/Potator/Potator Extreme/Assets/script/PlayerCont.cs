using UnityEngine;
using System.Collections;

public class PlayerCont : MonoBehaviour {

    private Animator animator;
    private SpriteRenderer spr;
    private bool derecha;
    public GameObject gameManager;
    public bool suelo;
    public float velocidad;
    public float salto;
    private Rigidbody rb;
    public float tolerancia;
    public float lastY;
    public int vidas;

	// Use this for initialization
	void Start () {
        derecha = true;
        animator = this.gameObject.GetComponent<Animator>();
        spr = this.gameObject.GetComponent<SpriteRenderer>();
        suelo = true;
        rb = this.gameObject.GetComponent<Rigidbody>();
        vidas = 5;
	}
	
	// Update is called once per frame
	void Update () {
        if (vidas > 0) {
            // Debug.Log(this.transform.position.y);
            /*
             if(this.transform.position.y-lastY > (tolerancia*(-1)) && this.transform.position.y - lastY < tolerancia)
             {
                 suelo = true;
                 Debug.Log("puto suelo");
             }
             else
             {
                 suelo = false;
             }
             */



            if (suelo)
            {
                if (Input.GetAxis("Horizontal") != 0)
                {
                    animator.Play("WalkRt");
                    if (Input.GetAxis("Horizontal") < 0)
                    {
                        derecha = true;
                        spr.flipX = true;
                    }
                    else
                    {
                        derecha = false;
                        spr.flipX = false;
                    }
                }
                else
                {
                    if (Input.GetButton("Fire1"))
                    {
                        animator.Play("Fire");
                        gameManager.GetComponent<GameManager>().disparar(derecha, transform.position.x, transform.position.y);
                    }
                    else
                    {
                        if (Input.GetAxis("Vertical") < 0)
                        {
                            animator.Play("Duck");
                        }
                        else
                        {
                            if (Input.GetAxis("Vertical") > 0)
                            {
                                rb.AddForce(new Vector3(0, 1) * salto);
                                animator.Play("Jump");
                                suelo = false;

                            }
                            else
                            {
                                animator.Play("Idle");
                            }
                        }
                    }
                }
            }
            else
            {
                animator.Play("BOOM");
            }
            
        }

        Vector3 fuerza = new Vector3(1f, 0) * Input.GetAxis("Horizontal") * velocidad * Time.deltaTime;
        //Debug.Log(fuerza);
        transform.position += fuerza;
        
        lastY = this.transform.position.y;
	}

    

}
