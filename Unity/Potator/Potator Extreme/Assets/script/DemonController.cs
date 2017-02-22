using UnityEngine;
using System.Collections;

public class DemonController : MonoBehaviour {
    public int hits;
    public Animator animator;
    private float contador;
    private bool muerto;
    public GameObject disparo;
    public GameManager gm;

	// Use this for initialization
	void Start () {
        muerto = false;
        hits = 0;
        contador = 0;
        animator.Play("Idle");
	}
	
	// Update is called once per frame
	void Update () {
        if (hits > 90)
        {
            muerto = true;
            animator.Play("Die");
        }
	    if(contador > 2 && !muerto)
        {
            animator.Play("Attack");
            contador = 0;
            Invoke("Shoot", 0.5f);
        }
        else
        {
            contador += Time.deltaTime;
        }
	}

    public void Shoot()
    {
        
        GameObject shot = Instantiate(disparo, transform.position + new Vector3(0,0.5f,0), Quaternion.identity);
        shot.GetComponent<AtaqueDemonController>().player = this.gm.masterChief;
    }

}